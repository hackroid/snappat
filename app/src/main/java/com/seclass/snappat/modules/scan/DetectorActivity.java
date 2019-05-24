/*
 * Copyright 2016 The TensorFlow Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.seclass.snappat.modules.scan;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.media.ImageReader.OnImageAvailableListener;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.util.Size;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.lzy.okgo.model.Response;
import com.seclass.snappat.app.ActivityUtils;
import com.seclass.snappat.base.BaseUrl;
import com.seclass.snappat.bean.CommonResponse;
import com.seclass.snappat.modules.publish.PublishActivity;
import com.seclass.snappat.modules.scanresult.ResActivity;
import com.seclass.snappat.net.HttpUtils;
import com.seclass.snappat.net.callbck.JsonCallback;
import com.seclass.snappat.utils.ToastUtils;
import com.seclass.snappat.utils.Utils;
import com.seclass.snappat.view.OverlayView;
import com.seclass.snappat.view.OverlayView.DrawCallback;
import com.seclass.snappat.modules.scan.env.BorderedText;
import com.seclass.snappat.modules.scan.env.ImageUtils;
import com.seclass.snappat.modules.scan.env.Logger;
import com.seclass.snappat.modules.scan.tracking.MultiBoxTracker;
import com.seclass.snappat.R;

import org.json.JSONArray;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 * An activity that uses a TensorFlowMultiBoxDetector and ObjectTracker to detect and then track
 * objects.
 */
public class DetectorActivity extends CameraActivity implements OnImageAvailableListener {
  private static final Logger LOGGER = new Logger();

  private static final int TF_OD_API_INPUT_SIZE = 300;
  private static final String TF_OD_API_MODEL_FILE =
      "file:///android_asset/ssd_mobilenet_v1_android_export.pb";
  private static final String TF_OD_API_LABELS_FILE = "file:///android_asset/coco_labels_list.txt";

  // Minimum detection confidence to track a detection.
  private static final float MINIMUM_CONFIDENCE_TF_OD_API = 0.6f;

  private static final Size DESIRED_PREVIEW_SIZE = new Size(640, 480);

  private static final boolean SAVE_PREVIEW_BITMAP = false;
  private static final float TEXT_SIZE_DIP = 10;

  private Integer sensorOrientation;

  private Classifier detector;

  private long lastProcessingTimeMs;
  private Bitmap rgbFrameBitmap = null;
  private Bitmap croppedBitmap = null;
  private Bitmap cropCopyBitmap = null;

  private boolean computingDetection = false;

  private long timestamp = 0;

  private Matrix frameToCropTransform;
  private Matrix cropToFrameTransform;

  private MultiBoxTracker tracker;

  private byte[] luminanceCopy;

  private BorderedText borderedText;

  private ImageButton snap_btn;
  private String[] reg_result;

  public class ScanThread implements Runnable {

    private volatile String[][] targets;

    public ScanThread(String[][] target_list) {
      this.targets = target_list;
    }

    private boolean stringListEqual(String[] s1, String[] s2) {
      if (s1 == null || s2 == null || s1.length == 0) return false;
      for (int i = 0; i < s1.length; i++) {
        Log.d("Debug", "comparison for str: " + s1[i] + " and " + s2[i]);
        if ((s1[i] != null && s2[i] != null && !s1[i].equals(s2[i]))
            || s1[i] == null
            || s2[i] == null) {
          return false;
        }
      }
      return true;
    }

    @Override
    public void run() {
      Bundle bundle = new Bundle();
      while (true) {
        boolean flag = true;
        if (reg_result == null) {
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            continue;
          }
        }

        if (targets != null) {
          Log.d("targets", "targets!=null");
          Log.d("reg_result", Arrays.toString(reg_result));
          for (int i = 0; i < targets.length; ++i) {
            if (targets[i] != null) {
              Log.d("targets[i]", i + Arrays.toString(targets[i]));
            }
            if (targets[i] != null
                && cropCopyBitmap != null
                && stringListEqual(reg_result, targets[i])
                && Bitmap2Bytes(cropCopyBitmap) != null) {
              bundle.putByteArray("img", Bitmap2Bytes(cropCopyBitmap));
              bundle.putStringArray("result", reg_result);
              bundle.putString("treasure", treasure[i]);
              bundle.putString("coins", coins[i]);
              Log.d("reg_result", "judge: " + Arrays.toString(reg_result));
              ActivityUtils.next(DetectorActivity.this, ResActivity.class, bundle, true);
              flag = false;
              break;
            }
          }
        }
        if (!flag) break;
      }
    }
  }

  @Override
  void startScanThread() {
    //    String[][] scan_targets = fetch_scan();
    fetch_scan();
    new Thread(new ScanThread(test_data)).start();
  }

  String[][] test_data = new String[100][100];
  String[] treasure = new String[100];
  String[] coins = new String[100];

  public void getPuzzleSucc(JSONArray objects) {
    Log.i("ingetPuzzleSucc", "start getPuzzleSucc method");
    Log.d("Debug", "getPuzzleInfo Response Class:" + objects.toString());
    try {
      for (int i = 0; i < objects.length(); i++) {
        JSONObject goodsEntityObject = objects.getJSONObject(i);
        String key = goodsEntityObject.getString("key");
        Log.d("key", key);
        String[] key_words = key.split(" ");
        if (key_words.length != 0) {
          for (int j = 0; j < key_words.length; j++) {
            test_data[i][j] = key_words[j];
            if (key_words[j] != null) {
              Log.d("key_words", key_words[j]);
            }
          }
          treasure[i] = goodsEntityObject.getString("treasure");
          coins[i] = goodsEntityObject.getString("coins");
        }
      }
    } catch (Exception e) {
      Log.d("Exception", "getPuzzleSucc" + e);
    }
  }

  public void getPuzzleInfo() {
    String phone_number = Utils.getSpUtils().getString("phone_number");
    String username = Utils.getSpUtils().getString("user_name");
    HashMap<String, String> hashMap = new HashMap<String, String>();
    hashMap.put("phone", phone_number);
    hashMap.put("username", username);
    Log.d("DataTest", phone_number);
    Log.d("DataTest", username);
    HttpUtils.postRequest(
        BaseUrl.HTTP_Get_mystery,
        DetectorActivity.this,
        hashMap,
        new JsonCallback<CommonResponse<CommonResponse.Test>>() {
          @Override
          public void onSuccess(Response<CommonResponse<CommonResponse.Test>> response) {
            JSONArray puzzleResponseList = new JSONArray();
            Log.d("Debug", "getPuzzleInfo Response: " + response);
            if (response.body().errno == 0) {
              try {
                puzzleResponseList = new JSONArray(response.body().getData().dataString);
                Log.d("Debug", "getPuzzleInfo Response Class:" + puzzleResponseList.toString());
              } catch (Exception e) {
                Log.d("Exception", "getPuzzleInfo Response: null object reference" + e);
              }

              // Success
              getPuzzleSucc(puzzleResponseList);

            } else {
              Log.d("Errno", "Errno when get keywords list" + response.body().errmsg);
            }
          }
        });
  }

  private void fetch_scan() {
    getPuzzleInfo();
  }

  @Override
  public void onPreviewSizeChosen(final Size size, final int rotation) {
    snap_btn = findViewById(R.id.snap);
    if (isDebug()) {
      snap_btn.setVisibility(View.INVISIBLE);
    } else {
      snap_btn.setVisibility(View.VISIBLE);
    }
    snap_btn.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            ToastUtils.showShortToast("Snap!");
            Bundle bundle = new Bundle();
            bundle.putByteArray("img", Bitmap2Bytes(cropCopyBitmap));
            bundle.putStringArray("result", reg_result);
            ActivityUtils.next(DetectorActivity.this, PublishActivity.class, bundle, true);
          }
        });

    final float textSizePx =
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, TEXT_SIZE_DIP, getResources().getDisplayMetrics());
    borderedText = new BorderedText(textSizePx);
    borderedText.setTypeface(Typeface.MONOSPACE);

    tracker = new MultiBoxTracker(this);

    int cropSize = TF_OD_API_INPUT_SIZE;

    try {
      detector =
          TensorFlowObjectDetectionAPIModel.create(
              getAssets(), TF_OD_API_MODEL_FILE, TF_OD_API_LABELS_FILE, TF_OD_API_INPUT_SIZE);
      cropSize = TF_OD_API_INPUT_SIZE;
    } catch (final IOException e) {
      LOGGER.e("Exception initializing classifier!", e);
      Toast toast =
          Toast.makeText(
              getApplicationContext(), "Classifier could not be initialized", Toast.LENGTH_SHORT);
      toast.show();
      finish();
    }

    previewWidth = size.getWidth();
    previewHeight = size.getHeight();

    sensorOrientation = rotation - getScreenOrientation();
    LOGGER.i("Camera orientation relative to screen canvas: %d", sensorOrientation);

    LOGGER.i("Initializing at size %dx%d", previewWidth, previewHeight);
    rgbFrameBitmap = Bitmap.createBitmap(previewWidth, previewHeight, Config.ARGB_8888);
    croppedBitmap = Bitmap.createBitmap(cropSize, cropSize, Config.ARGB_8888);

    frameToCropTransform =
        ImageUtils.getTransformationMatrix(
            previewWidth, previewHeight,
            cropSize, cropSize,
            sensorOrientation, false);

    cropToFrameTransform = new Matrix();
    frameToCropTransform.invert(cropToFrameTransform);

    trackingOverlay = (OverlayView) findViewById(R.id.tracking_overlay);
    trackingOverlay.addCallback(
        new DrawCallback() {
          @Override
          public void drawCallback(final Canvas canvas) {
            tracker.draw(canvas);
            if (isDebug()) {
              tracker.drawDebug(canvas);
            }
          }
        });

    addCallback(
        new DrawCallback() {
          @Override
          public void drawCallback(final Canvas canvas) {
            if (!isDebug()) {
              return;
            }
            final Bitmap copy = cropCopyBitmap;
            if (copy == null) {
              return;
            }

            final int backgroundColor = Color.argb(100, 0, 0, 0);
            canvas.drawColor(backgroundColor);

            final Matrix matrix = new Matrix();
            final float scaleFactor = 2;
            matrix.postScale(scaleFactor, scaleFactor);
            matrix.postTranslate(
                canvas.getWidth() - copy.getWidth() * scaleFactor,
                canvas.getHeight() - copy.getHeight() * scaleFactor);
            canvas.drawBitmap(copy, matrix, new Paint());

            final Vector<String> lines = new Vector<String>();
            if (detector != null) {
              final String statString = detector.getStatString();
              final String[] statLines = statString.split("\n");
              for (final String line : statLines) {
                lines.add(line);
              }
            }
            lines.add("");
            lines.add("Inference time: " + lastProcessingTimeMs + "ms");

            borderedText.drawLines(canvas, 10, canvas.getHeight() - 10, lines);
          }
        });
  }

  OverlayView trackingOverlay;

  @Override
  protected void processImage() {
    ++timestamp;
    final long currTimestamp = timestamp;
    byte[] originalLuminance = getLuminance();
    tracker.onFrame(
        previewWidth,
        previewHeight,
        getLuminanceStride(),
        sensorOrientation,
        originalLuminance,
        timestamp);
    trackingOverlay.postInvalidate();

    // No mutex needed as this method is not reentrant.
    if (computingDetection) {
      readyForNextImage();
      return;
    }
    computingDetection = true;
    LOGGER.i("Preparing image " + currTimestamp + " for detection in bg thread.");

    rgbFrameBitmap.setPixels(getRgbBytes(), 0, previewWidth, 0, 0, previewWidth, previewHeight);

    if (luminanceCopy == null) {
      luminanceCopy = new byte[originalLuminance.length];
    }
    System.arraycopy(originalLuminance, 0, luminanceCopy, 0, originalLuminance.length);
    readyForNextImage();

    final Canvas canvas = new Canvas(croppedBitmap);
    canvas.drawBitmap(rgbFrameBitmap, frameToCropTransform, null);
    // For examining the actual TF input.
    if (SAVE_PREVIEW_BITMAP) {
      ImageUtils.saveBitmap(croppedBitmap);
    }

    runInBackground(
        new Runnable() {
          @Override
          public void run() {
            LOGGER.i("Running detection on image " + currTimestamp);
            final long startTime = SystemClock.uptimeMillis();
            final List<Classifier.Recognition> results = detector.recognizeImage(croppedBitmap);
            lastProcessingTimeMs = SystemClock.uptimeMillis() - startTime;

            cropCopyBitmap = Bitmap.createBitmap(croppedBitmap);
            final Canvas canvas = new Canvas(cropCopyBitmap);
            final Paint paint = new Paint();
            paint.setColor(Color.RED);
            paint.setStyle(Style.STROKE);
            paint.setStrokeWidth(2.0f);

            final List<Classifier.Recognition> mappedRecognitions =
                new LinkedList<Classifier.Recognition>();

            for (final Classifier.Recognition result : results) {
              final RectF location = result.getLocation();
              if (location != null && result.getConfidence() >= MINIMUM_CONFIDENCE_TF_OD_API) {
                canvas.drawRect(location, paint);

                cropToFrameTransform.mapRect(location);
                result.setLocation(location);
                mappedRecognitions.add(result);
              }
            }

            reg_result = new String[mappedRecognitions.size()];
            for (int i = 0; i < mappedRecognitions.size(); ++i) {
              reg_result[i] =
                  ((LinkedList<Classifier.Recognition>) mappedRecognitions).pop().getTitle();
            }

            tracker.trackResults(mappedRecognitions, luminanceCopy, currTimestamp);
            trackingOverlay.postInvalidate();

            requestRender();
            computingDetection = false;
          }
        });
  }

  @Override
  protected int getLayoutId() {
    return R.layout.camera_connection_fragment_tracking;
  }

  @Override
  protected Size getDesiredPreviewFrameSize() {
    return DESIRED_PREVIEW_SIZE;
  }

  @Override
  public void onSetDebug(final boolean debug) {
    detector.enableStatLogging(debug);
  }

  public byte[] Bitmap2Bytes(Bitmap bm) {
    if (bm == null) return null;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
    return baos.toByteArray();
  }
}
