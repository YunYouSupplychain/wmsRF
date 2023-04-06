package com.yunyou.androidwmsrf.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yunyou.androidwmsrf.R;
import com.yunyou.androidwmsrf.app.AppLifecyclesImpl;
import com.yunyou.androidwmsrf.app.IToolbar;
import com.yunyou.androidwmsrf.di.component.DaggerShipmentByTrackingNoComponent;
import com.yunyou.androidwmsrf.di.module.ShipmentByTrackingNoModule;
import com.yunyou.androidwmsrf.mvp.contract.ShipmentByTrackingNoContract;
import com.yunyou.androidwmsrf.mvp.model.webservice.SaveShipmentRequest;
import com.yunyou.androidwmsrf.mvp.presenter.ShipmentByTrackingNoPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 按面单号发货
 *
 * @author WMJ
 * @version 2019/10/23
 */
public class ShipmentByTrackingNoActivity extends BaseActivity<ShipmentByTrackingNoPresenter> implements ShipmentByTrackingNoContract.View, IToolbar {
    @BindView(R.id.et_box_num)
    EditText etBoxNum;

    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    private String mBoxNum;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        // 如找不到该类,请编译一下项目
        DaggerShipmentByTrackingNoComponent
                .builder()
                .appComponent(appComponent)
                .shipmentByTrackingNoModule(new ShipmentByTrackingNoModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        // 如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
        return R.layout.activity_shipment_by_tracking_no;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initTrackingNoInput();
        etBoxNum.requestFocus();
    }

    @Override
    public void showLoading() {
        progressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressbar.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.makeText(this, message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @OnClick({R.id.btn_cancel, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 取消
            case R.id.btn_cancel:
                killMyself();
                break;

            // 确认
            case R.id.btn_confirm:
                callConfirmEvent();
                break;
        }
    }

    @Override
    public boolean isShowBackButton() {
        return true;
    }

    private void initTrackingNoInput() {
        etBoxNum.setOnEditorActionListener((TextView v, int actionId, KeyEvent event) -> {
            // 当actionId == XX_SEND 或者 XX_DONE时都触发
            // 或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
            // 注意，这是一定要判断event != null。因为在某些输入法上会返回null。
            if (actionId == EditorInfo.IME_ACTION_SEND
                    || actionId == EditorInfo.IME_ACTION_DONE
                    || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                // 处理事件
                callConfirmEvent();
            }
            return false;
        });
    }

    private void callConfirmEvent() {
        mBoxNum = etBoxNum.getText().toString().trim();
        if (TextUtils.isEmpty(mBoxNum)) {
            showMessage(getResources().getString(R.string.please_scan_or_input_face_num));
            etBoxNum.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }
        mPresenter.saveShipment(new SaveShipmentRequest("", "", mBoxNum));
    }

    @Override
    public void afterSave() {
        etBoxNum.setText("");
    }

}