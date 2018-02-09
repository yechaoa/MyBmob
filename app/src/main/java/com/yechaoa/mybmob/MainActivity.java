package com.yechaoa.mybmob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBmob();

        initView();

        initSaveData();

    }

    private void initView() {
        mTextView = findViewById(R.id.textView);
        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                queryList();
            }
        });
    }

    private void initBmob() {
        //提供以下两种方式进行初始化操作：

        //第一：默认初始化
        Bmob.initialize(this, "9d517b70f303aa5295db0aa02b7bbed6");
        //Bmob.initialize(this, "9d517b70f303aa5295db0aa02b7bbed6","Bmob");
        // 注:自v3.5.2开始，数据sdk内部缝合了统计sdk，开发者无需额外集成，传渠道参数即可，不传默认没开启数据统计功能
        //Bmob.initialize(this, "Your Application ID","bmob");

        //第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        //BmobConfig config =new BmobConfig.Builder(this)
        ////设置appkey
        //.setApplicationId("Your Application ID")
        ////请求超时时间（单位为秒）：默认15s
        //.setConnectTimeout(30)
        ////文件分片上传时每片的大小（单位字节），默认512*1024
        //.setUploadBlockSize(1024*1024)
        ////文件的过期时间(单位为秒)：默认1800s
        //.setFileExpiration(2500)
        //.build();
        //Bmob.initialize(config);
    }

    private void initSaveData() {
        Person p2 = new Person();
        p2.setName("yechaoa");
        p2.setAddress("上海浦东");
        p2.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if (e == null) {
                    toast("添加数据成功，返回objectId为：" + objectId);
                    mTextView.setText("添加数据成功，返回objectId为：" + objectId);
                } else {
                    toast("创建数据失败：" + e.getMessage());
                    mTextView.setText("创建数据失败：" + e.getMessage());
                }
            }
        });
    }


    private void queryList() {
        BmobQuery<Person> query = new BmobQuery<Person>();
        //查询name叫“yechaoa”的数据
        query.addWhereEqualTo("name", "yechaoa");
        //返回50条数据，如果不加上这条语句，默认返回10条数据
        query.setLimit(50);
        //执行查询方法
        query.findObjects(new FindListener<Person>() {
            @Override
            public void done(List<Person> object, BmobException e) {
                if (e == null) {
                    toast("查询成功：共" + object.size() + "条数据。");
                    mTextView.setText("查询成功：共" + object.size() + "条数据。");
                    for (Person person : object) {
                        //获得name的信息
                        person.getName();
                        //获得数据的objectId信息
                        person.getObjectId();
                        //获得createdAt数据创建时间（注意是：createdAt，不是createAt）
                        person.getCreatedAt();
                    }
                } else {
                    toast("失败：" + e.getMessage() + "," + e.getErrorCode());
                    mTextView.setText("失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });
    }


    private void toast(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
    }

}
