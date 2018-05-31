package googleroom.android.com.google_room.douban

import googleroom.android.com.google_room.net.ok.HttpCallBack
import googleroom.android.com.google_room.net.ok.OkHttpModel
import googleroom.android.com.google_room.rxutil.BaseSchedulerProvider
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.json.JSONObject

/**
 *  Created By handan
 *  CreateDate: 2018/5/29
 *  Desc:
 */
class DouBanPresenter(view: DouBanContract.View,
                      baseSchedulerProvider: BaseSchedulerProvider) : DouBanContract.Presenter {

    private var mBaseSchedulerProvider = baseSchedulerProvider
    private var mView = view
    private var mCompositeDisposable: CompositeDisposable = CompositeDisposable()
    lateinit var disposable: Disposable


    override fun subscribe() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unSubscribe() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getDouBanMovies() {
        Observable.create(object : ObservableOnSubscribe<JSONObject> {
            override fun subscribe(e: ObservableEmitter<JSONObject>) {
                OkHttpModel().get(object : HttpCallBack.JsonCallback {
                    override fun onResponse(responseJson: JSONObject) {
                        e.onNext(responseJson)
                    }

                    override fun onFailure(code: Int, e: Exception) {
                    }
                })
            }
        }).subscribeOn(mBaseSchedulerProvider.io())
                .observeOn(mBaseSchedulerProvider.ui())
                .subscribe(object : Observer<JSONObject> {
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: JSONObject) {
                    }

                    override fun onError(e: Throwable) {
                    }
                })
    }
}