package com.qkxmall.mall.views;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.qkxmall.mall.BitmapUtils;
import com.qkxmall.mall.R;
import com.qkxmall.mall.SelectImageUtils;

public class EvaluateActivity extends Activity {
    private ImageView backup = null;
    private Button submit = null;
    private TextView addPicture = null;
    private RatingBar ratingBar1 = null;
    private RatingBar ratingBar2 = null;
    private EditText evaluate = null;

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;

//    Bitmap bitmap1;
//    Bitmap bitmap2;
//    Bitmap bitmap3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);
        init();

        backup.setOnClickListener(new OnClickListeners());
        submit.setOnClickListener(new OnClickListeners());
        addPicture.setOnClickListener(new OnClickListeners());
        imageView1.setOnClickListener(new OnClickListeners());
        imageView2.setOnClickListener(new OnClickListeners());
        imageView3.setOnClickListener(new OnClickListeners());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            String path = SelectImageUtils.getPathByUri4kitkat(this, uri);
            Bitmap bitmap = BitmapUtils.compressImageFromFile(this, path, 1);
            if (imageView1.getDrawable() == null) {
                imageView1.setImageBitmap(bitmap);
            }else if (imageView2.getDrawable() == null) {
                imageView2.setImageBitmap(bitmap);
            }else if (imageView3.getDrawable() == null) {
                imageView3.setImageBitmap(bitmap);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void init() {
        backup = (ImageView) findViewById(R.id.evaluate_backup);
        submit = (Button) findViewById(R.id.evaluate_submit);
        addPicture = (TextView) findViewById(R.id.evaluate_add_picture);
        imageView1 = (ImageView) findViewById(R.id.iv01);
        imageView2 = (ImageView) findViewById(R.id.iv02);
        imageView3 = (ImageView) findViewById(R.id.iv03);
        ratingBar1 = (RatingBar) findViewById(R.id.evaluate_describe_rating);
        ratingBar2 = (RatingBar) findViewById(R.id.evaluate_di_rating);
        evaluate = (EditText) findViewById(R.id.evaluate_evaluate_info);
    }

    private class OnClickListeners implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.evaluate_backup:
                    finish();
                    break;
                case R.id.evaluate_submit:
                    Toast.makeText(EvaluateActivity.this
                            ,"服务评分："+ratingBar1.getRating()+"\n"
                            +"物流评分:"+ratingBar2.getRating()+"\n"
                            +"评论信息："+evaluate.getText().toString().trim()+"\n"
                            +"提交成功!",Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                case R.id.evaluate_add_picture:
                    if (imageView1.getDrawable() != null && imageView2.getDrawable() != null && imageView3.getDrawable() != null){
                        Toast.makeText(EvaluateActivity.this,"最多可添加三张图片!",Toast.LENGTH_SHORT).show();
                    }else {
                        Intent intent = new Intent();
                        /* 开启Pictures画面Type设定为image */
                        intent.setType("image/*");
                        /* 使用Intent.ACTION_GET_CONTENT这个Action */
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                         /* 取得相片后返回本画面 */
                        startActivityForResult(intent, 1);
                    }
                    break;
                case R.id.iv01:
                    imageView1.setImageDrawable(null);
                    break;
                case R.id.iv02:
                    imageView2.setImageDrawable(null);
                    break;
                case R.id.iv03:
                    imageView3.setImageDrawable(null);
                    break;
            }
        }
    }
}
