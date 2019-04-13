## 可能用到的Android技术总结

### Activity页面跳转

更多详细跳转参考 [Intent详细说明](www.baidu.com)

```java
//从SplashActivity 跳转到 MainActivity, 不再返回，不传数据
Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
//点击id为button_id的按钮跳转
imgV = (Button) findViewById(R.id.button_id);
imgV.setOnClickListener(new View.OnClickListener() {//创建监听器
    @Override
    public void onClick(View view) {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
    }
});
//设定计时器在一定时间内跳转
int time=5; //5s后跳转,这个变量声明在类开始的地方

Timer timer = new Timer();
timer.scheduleAtFixedRate(new TimerTask() {
    @Override
    public void run() {
        time--;
        if (time==0){
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}, 0, 2000);
```

