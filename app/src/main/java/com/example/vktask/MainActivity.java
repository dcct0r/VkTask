package com.example.vktask;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, init {

    private ImageButton videoCamera, microphone, rightIcon
            ,peopleGroup, mes, expandMore, bottomPanel;
    private boolean flag = true;
    private GridView gridView;
    private GridAdapter gridAdapter;
    private List<ImageButton> imageButtons;
    private ConstraintLayout userGridWrapper;
    private static final int[] BUTTON_IDS = {
            R.id.call_end,
            R.id.hand_hello,
            R.id.bottomTick,
            R.id.mic_off,
            R.id.expand_upper,
            R.id.right_icon,
            R.id.cam_off,
            R.id.sms,
            R.id.group_of_people
    };
    private final List<String> uName = Arrays.asList("You", "Test nickname to make sure we can cut off our string in case of long name", "Glen Campbell", "Sam Cooke");
    private final Integer[] uIcons = new Integer[]{R.drawable.ic_user8, R.drawable.ic_user2, R.drawable.ic_user7, R.drawable.ic_user3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialise();
    }

    @Override
    public void initialise() {
        gridView = findViewById(R.id.currentUserSession);
        userGridWrapper = findViewById(R.id.gridViewWrapper);
        imageButtons = new ArrayList<ImageButton>(BUTTON_IDS.length);
        gridAdapter = new GridAdapter(MainActivity.this, uName, uIcons);

        for(int id: BUTTON_IDS) {
            ImageButton imageButton = findViewById(id);
            imageButton.setOnClickListener(this);
        }

        microphone = findViewById(R.id.mic_off);
        expandMore = findViewById(R.id.expand_upper);
        rightIcon = findViewById(R.id.right_icon);
        videoCamera = findViewById(R.id.cam_off);
        mes = findViewById(R.id.sms);
        peopleGroup = findViewById(R.id.group_of_people);
        bottomPanel = findViewById(R.id.bottomTick);

        gridView.setAdapter(gridAdapter);
    }

    private int convertToDp(int num) {
        int dpSize = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, num, getResources()
                        .getDisplayMetrics());
        return dpSize;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

            peopleGroup.setVisibility(View.GONE);
            mes.setVisibility(View.GONE);
            rightIcon.setVisibility(View.GONE);
            expandMore.setVisibility(View.GONE);
            bottomPanel.setVisibility(View.GONE);

            ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(0, 0);

            int sizeInDP = convertToDp(8);

            params.topToTop = R.id.bigMom;
            params.bottomToTop = R.id.bottomTick;
            params.startToStart = R.id.bigMom;
            params.endToEnd = R.id.bigMom;
            params.setMargins(sizeInDP, sizeInDP, sizeInDP, sizeInDP);
            gridView.setNumColumns(GridView.AUTO_FIT);
            gridView.setHorizontalSpacing(sizeInDP);
            userGridWrapper.setLayoutParams(params);

        } else {

            ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(0, 0);

            int sizeInDP = convertToDp(8);

            params.topToTop = R.id.upper_bottom_line;
            params.bottomToTop = R.id.aboveMinimizeLine;
            params.startToStart = R.id.bigMom;
            params.endToEnd = R.id.bigMom;
            params.leftMargin = sizeInDP;
            params.rightMargin = sizeInDP;
            gridView.setNumColumns(1);
            userGridWrapper.setLayoutParams(params);

            peopleGroup.setVisibility(View.VISIBLE);
            mes.setVisibility(View.VISIBLE);
            rightIcon.setVisibility(View.VISIBLE);
            expandMore.setVisibility(View.VISIBLE);
            bottomPanel.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cam_off:
                if(flag) {
                    flag = false;
                    videoCamera.setImageResource(R.drawable.videocam_24);
                    view.setBackgroundResource(R.drawable.grey_border_radius);
                }
                else {
                    flag = true;
                    videoCamera.setImageResource(R.drawable.videocam_off_24);
                    view.setBackgroundResource(R.drawable.white_border_radius);
                }
                break;
            case R.id.hand_hello:
                new AlertDialog.Builder(MainActivity.this)
                        .setMessage("привет")
                        .setNegativeButton(android.R.string.no, null)
                        .show();
                break;
            case R.id.mic_off:
                if(flag) {
                    flag = false;
                    microphone.setImageResource(R.drawable.mic_24);
                    view.setBackgroundResource(R.drawable.grey_border_radius);
                }
                else {
                    flag = true;
                    microphone.setImageResource(R.drawable.mic_off_24);
                    view.setBackgroundResource(R.drawable.white_border_radius);
                }
                break;
            case R.id.call_end:
                finishAndRemoveTask();
                System.exit(0);
                break;
            case R.id.sms:
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_APP_MESSAGING);
                startActivity(intent);
                break;
            case R.id.group_of_people:
                Intent intent1 = new Intent(MainActivity.this, ContactUser.class);
                startActivity(intent1);
                break;
            case R.id.right_icon:
                Collections.reverse(uName);
                Collections.reverse(Arrays.asList(uIcons));
                gridAdapter = new GridAdapter(MainActivity.this, uName, uIcons);
                gridView.setAdapter(gridAdapter);
        }
    }
}