package geekbrains.ru.translator

import geekbrains.ru.translator.model.data.AppState
import geekbrains.ru.translator.model.data.DataModel
import geekbrains.ru.translator.model.datasource.DataSourceLocal
import geekbrains.ru.translator.model.datasource.DataSourceRemote
import geekbrains.ru.translator.model.repository.Repository
import geekbrains.ru.translator.model.repository.RepositoryImplementation
import geekbrains.ru.translator.rx.SchedulerProvider
import geekbrains.ru.translator.view.base.View
import geekbrains.ru.translator.view.main.MainInteractor
import geekbrains.ru.translator.view.main.MainPresenterImpl
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations
import java.lang.NullPointerException

class MainPresenterTest {
    private val remoteRepository = RepositoryImplementation(DataSourceRemote())
    private val localRepository = RepositoryImplementation(DataSourceLocal())
    private var interactor= MainInteractor(remoteRepository,localRepository)
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val schedulerProvider: SchedulerProvider = SchedulerProvider()
    private lateinit var presenter:MainPresenterImpl<AppState, View>
    private lateinit var view:View
    @Before
    fun setUp(){
        RxAndroidPlugins.setInitMainThreadSchedulerHandler({scheduler -> Schedulers.trampoline()})
        compositeDisposable=Mockito.mock(CompositeDisposable::class.java)
        presenter = MainPresenterImpl(interactor,compositeDisposable,schedulerProvider)
        view=Mockito.mock(View::class.java)
    }
    @Test
    fun attachView_Test(){
        presenter.attachView(view)
        presenter.getData("",true)
        Mockito.verify(view).renderData(AppState.Loading(null))
    }
    @Test
    fun detachView_Test(){
        presenter.attachView(view)
        presenter.detachView(view)
        Mockito.verify(compositeDisposable).clear()
    }
    @Test
    fun getData_Test(){
        presenter.attachView(view)
        presenter.getData("",true)
        Mockito.verify(view).renderData(AppState.Loading(null))
    }

}