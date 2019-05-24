/*
 * Created by Snooow on 2019/5/10
 */

package com.seclass.snappat.modules.mine;

import com.seclass.snappat.base.BaseFragment;
import com.seclass.snappat.base.BaseView;
import com.seclass.snappat.bean.CommonResponse;
import org.greenrobot.greendao.annotation.NotNull;
import org.json.JSONObject;

/**
 * Interface {@code MineView}.
 *
 * <p>Interface for self information page.
 *
 * <p>extends class:
 *
 * <p>{@link BaseView}
 *
 * @author <a href="11612717@mail.sustech.edu.com">Tao Ren</a>
 * @since 3.0
 */
public interface MineView extends BaseView {

  /**
   * active when get user information successfully
   *
   * @param msg {@link JSONObject}
   * @since 3.0
   */
  void getUserInfoSucc(@NotNull JSONObject msg);

  /**
   * active when get user information unsuccessfully
   *
   * @param msg {@link CommonResponse<CommonResponse.Test>}
   * @since 3.0
   */
  void getUserInfoFail(@NotNull CommonResponse<CommonResponse.Test> msg);

  /**
   * active when get history information successfully
   *
   * @param msg {@link JSONObject}
   * @since 3.0
   */
  void getHistoryInfoSucc(@NotNull JSONObject msg);

  /**
   * active when get history information unsuccessfully
   *
   * @param msg {@link CommonResponse<CommonResponse.Test>}
   * @since 3.0
   */
  void getHistoryInfoFail(@NotNull CommonResponse<CommonResponse.Test> msg);
}
