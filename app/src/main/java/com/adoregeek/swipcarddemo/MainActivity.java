package com.adoregeek.swipcarddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.adoregeek.swipcard.CardLayoutManager;
import com.adoregeek.swipcard.widget.SwipCardView2;
import com.adoregeek.swipcarddemo.adapter.CardListAdapter;
import com.adoregeek.swipcarddemo.bean.CardBean;
import com.adoregeek.swipcarddemo.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    protected SwipCardView2 swipCards;
    private CardListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        initView();
    }

    String jsonData = "[\n" +
            "        {\n" +
            "        \"url\": \"http://of6qychww.bkt.clouddn.com/image/jpg/facebook_logo.png\",\n" +
            "        \"title\": \"FaceBook\",\n" +
            "        \"updateTime\": \"2016-04-09\",\n" +
            "        \"updateUserId\": 1\n" +
            "        },\n" +
            "        {\n" +
            "        \"url\": \"http://of6qychww.bkt.clouddn.com/image/jpg/twitter_logo.png\",\n" +
            "        \"title\": \"Twitter\",\n" +
            "        \"updateTime\": \"2016-04-09\",\n" +
            "        \"updateUserId\": 1\n" +
            "        },\n" +
            "        {\n" +
            "        \"url\": \"http://of6qychww.bkt.clouddn.com/image/jpg/google_logo.png\",\n" +
            "        \"title\": \"Google\",\n" +
            "        \"updateTime\": \"2016-04-09\",\n" +
            "        \"updateUserId\": 1\n" +
            "        },\n" +
            "        {\n" +
            "        \"url\": \"http://of6qychww.bkt.clouddn.com/image/jpg/youtube_logo.png\",\n" +
            "        \"title\": \"Youtube\",\n" +
            "        \"updateTime\": \"2016-04-09\",\n" +
            "        \"updateUserId\": 1\n" +
            "        }\n" +
            "        ]";

    private void initView() {
        swipCards = (SwipCardView2) findViewById(R.id.swipCards);
        swipCards.setLayoutManager(new CardLayoutManager());
//        rlList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        List<CardBean> word = JsonUtil.jsonToEntityList(jsonData, CardBean.class);
        adapter = new CardListAdapter(this);
        swipCards.setAdapter(adapter);
        adapter.add(word);
        swipCards.setOnPostcardDismissListener(new SwipCardView2.OnPostcardDismissListener() {
            @Override
            public void onPostCardDismiss(int direction) {
                System.out.println("direction=" + direction);
                CardBean firstBean = adapter.getList().get(0);
                adapter.remove(firstBean);
                adapter.add(adapter.getItemCount() , firstBean);
                System.out.println("sdsa");
            }
        });
    }
}
