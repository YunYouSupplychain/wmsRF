package com.yunyou.androidwmsrf.mvp.model;

import android.app.Application;

import com.yunyou.androidwmsrf.mvp.contract.ScanSkuReceipt2Contract;
import com.yunyou.androidwmsrf.mvp.model.api.service.CommonService;
import com.yunyou.androidwmsrf.mvp.model.webservice.QueryAsnDetailByTraceIdOrSkuRequest;
import com.yunyou.androidwmsrf.mvp.model.webservice.QueryAsnDetailByTraceIdOrSkuResponse;
import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import io.reactivex.Observable;

@ActivityScope
public class ScanSkuReceipt2Model extends BaseModel implements ScanSkuReceipt2Contract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public ScanSkuReceipt2Model(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<QueryAsnDetailByTraceIdOrSkuResponse> queryAsnDetailByTraceIdOrSku(QueryAsnDetailByTraceIdOrSkuRequest request) {
        return Observable.just(mRepositoryManager
                .obtainRetrofitService(CommonService.class)
                .queryAsnDetailByTraceIdOrSku(request))
                // 不使用缓存
                .flatMap(observable -> observable);
    }

    @Override
    public Observable<QueryAsnDetailByTraceIdOrSkuResponse> selectSkuInfo(QueryAsnDetailByTraceIdOrSkuRequest request) {
        return Observable.just(mRepositoryManager
                .obtainRetrofitService(CommonService.class)
                .queryAsnDetailByTraceIdOrSku(request))
                // 不使用缓存
                .flatMap(observable -> observable);
    }
}