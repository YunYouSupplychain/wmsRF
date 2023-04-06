package com.yunyou.androidwmsrf.mvp.presenter;

import android.app.Application;

import com.yunyou.androidwmsrf.mvp.contract.ScanSkuReceiptSkuInfoContract;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

@ActivityScope
public class ScanSkuReceiptSkuInfoPresenter extends BasePresenter<ScanSkuReceiptSkuInfoContract.Model, ScanSkuReceiptSkuInfoContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public ScanSkuReceiptSkuInfoPresenter(ScanSkuReceiptSkuInfoContract.Model model, ScanSkuReceiptSkuInfoContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

}