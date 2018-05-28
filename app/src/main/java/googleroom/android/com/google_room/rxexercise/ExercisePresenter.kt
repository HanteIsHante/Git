package googleroom.android.com.google_room.rxexercise

import googleroom.android.com.google_room.data.bean.User
import io.reactivex.*
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscription
import java.util.*

/**
 *  Created By handan
 *  CreateDate: 2018/5/22
 *  Desc:
 */
class ExercisePresenter : ExerciseContract.Presenter {

    lateinit var mDisposable: CompositeDisposable

    override fun subscribe() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unSubscribe() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    fun exercise() {

        /*转换为另一种 发射器*/
        Observable.create(object : ObservableOnSubscribe<Int> {
            @Throws(Exception::class)
            override fun subscribe(e: ObservableEmitter<Int>) {
                if (!e.isDisposed) {
                    e.onNext(1001)
                }
            }
        })
                .map(object : io.reactivex.functions.Function<Int, String> {
                    override fun apply(t: Int): String {
                        return "hengha : $t"
                    }
                })
                .subscribe(object : Observer<String> {
                    override fun onNext(t: String) {
                        for (s in t.split(":")) {
                            if (s == "1001") {
                                mDisposable.dispose()
                            }
                        }
                    }

                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                        mDisposable.add(d)
                    }

                    override fun onError(e: Throwable) {
                    }
                })

//        -----------------------

        val observableString = Observable.create(object : ObservableOnSubscribe<String> {
            override fun subscribe(e: ObservableEmitter<String>) {
                e.onNext("this is a observable")
            }
        })

        val observableInt = Observable.create(object : ObservableOnSubscribe<Int> {
            override fun subscribe(e: ObservableEmitter<Int>) {
                e.onNext(1)
                e.onNext(2)
                e.onNext(3)
                e.onNext(4)
                e.onNext(5)
                e.onNext(6)
            }
        })

        /*多个发射器组合在一起*/
        Observable.zip(observableString, observableInt, object : io.reactivex.functions.BiFunction<String, Int, String> {
            override fun apply(t1: String, t2: Int): String {
                return t1 + t2
            }
        })
                .subscribe(object : Observer<String> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: String) {
                    }

                    override fun onError(e: Throwable) {
                    }
                })

//        ---------------------

        /*串联在一起*/
        Observable.concat(Observable.just(1, 2, 3), Observable.just(4, 5))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())

//        ---------------------------

        /*过滤重复内容*/
        var disposable = Observable
                .just(1, 2, 2, 2, 3, 4, 4)
                .distinct()
                .subscribe(object : Observer<Int> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onNext(t: Int) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onError(e: Throwable) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                })
//       --------------

//        ---------------
        Single.just(1)
                .subscribe(object : SingleObserver<Int> {
                    override fun onSuccess(t: Int) {


                    }

                    override fun onSubscribe(d: Disposable) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onError(e: Throwable) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                })


//        --------------------

        Observable.create(object : ObservableOnSubscribe<Objects> {
            override fun subscribe(e: ObservableEmitter<Objects>) {
            }
        })

//        ----------------

        Flowable.create(object : FlowableOnSubscribe<String> {
            override fun subscribe(e: FlowableEmitter<String>) {
                e.onNext("this is a flowable")
                e.onNext("this is a flowable")
                e.onComplete()
                e.onNext("this is a flowable")
            }
        }, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : FlowableSubscriber<String> {
                    override fun onSubscribe(s: Subscription) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onNext(t: String?) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onError(t: Throwable?) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onComplete() {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }
                })
        val subscribe1 =
                Flowable.create(object : FlowableOnSubscribe<String> {
                    override fun subscribe(e: FlowableEmitter<String>) {
                        e.onNext("---------")
                    }
                }, BackpressureStrategy.BUFFER)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(

                        )


        val subscribe = Flowable.just("").subscribe(object : FlowableSubscriber<String> {
            override fun onComplete() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onSubscribe(s: Subscription) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onNext(t: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onError(t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
        val list: List<User> = arrayListOf()
        Flowable.fromIterable(list)
                .subscribe(object : FlowableSubscriber<User> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(s: Subscription) {
                    }

                    override fun onNext(t: User?) {
                    }

                    override fun onError(t: Throwable?) {
                    }
                })
//---------------------
        Observable.create(object : ObservableOnSubscribe<String> {
            override fun subscribe(e: ObservableEmitter<String>) {

            }
        }).subscribe(
                
        )
    }
}