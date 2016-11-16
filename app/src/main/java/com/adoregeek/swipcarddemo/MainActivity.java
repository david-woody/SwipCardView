package com.adoregeek.swipcarddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.adoregeek.swipcard.CardLayoutManager;
import com.adoregeek.swipcard.widget.SwipCardView2;
import com.adoregeek.swipcarddemo.adapter.CardListAdapter;
import com.adoregeek.swipcarddemo.bean.CardBean;

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

    private void initView() {
        swipCards = (SwipCardView2) findViewById(R.id.swipCards);
        swipCards.setLayoutManager(new CardLayoutManager());
//        rlList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        List<CardBean> word = new ArrayList<>();
        word.add(new CardBean());
        word.add(new CardBean());
        word.add(new CardBean());
        word.add(new CardBean());
        word.add(new CardBean());
        word.add(new CardBean());
        adapter = new CardListAdapter(this);
        swipCards.setAdapter(adapter);
        adapter.add(word);
        swipCards.setOnPostcardDismissListener(new SwipCardView2.OnPostcardDismissListener() {
            @Override
            public void onPostcardDismiss(int direction) {
                CardBean firstBean = adapter.getList().get(adapter.getItemCount() - 1);
                adapter.remove(firstBean);
                adapter.add(0, firstBean);
            }
        });
    }
}
