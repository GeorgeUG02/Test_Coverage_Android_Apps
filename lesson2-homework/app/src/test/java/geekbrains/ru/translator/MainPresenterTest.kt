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
import io.reactivex.disposables.CompositeDisposable
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.lang.NullPointerException

class MainPresenterTest {
    private val remoteRepository: Repository<List<DataModel>> = RepositoryImplementation(DataSourceRemote())
    private val localRepository: Repository<List<DataModel>> = RepositoryImplementation(DataSourceLocal())
    private val interactor = MainInteractor(remoteRepository,localRepository)
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val schedulerProvider: SchedulerProvider = SchedulerProvider()
    private lateinit var presenter:MainPresenterImpl<AppState, View>
    private lateinit var view:View
    @Before
    fun setUp(){
        presenter = MainPresenterImpl(interactor,compositeDisposable,schedulerProvider)
        view=Mockito.mock(View::class.java)
    }
    @Test
    fun attachView_Test(){
        presenter.attachView(view)
        presenter.getData("",true)
        Mockito.verify(view).renderData(AppState.Loading(null))
    }
    @Test(expected = NullPointerException::class)
    fun detachView_Test(){
        presenter.attachView(view)
        presenter.detachView(view)
        Mockito.verify(compositeDisposable).clear()
        presenter.getData("",true)
    }
    @Test
    fun getData_Test(){
        presenter.attachView(view)
        var appState:AppState=Mockito.mock(AppState::class.java)
        Mockito.`when`(interactor.getData("",true)).thenReturn(remoteRepository.getData("").map { appState = AppState.Success(it)
            appState})
        presenter.getData("",true)
        val inOrder = Mockito.inOrder(schedulerProvider)
        inOrder.verify(schedulerProvider).io()
        inOrder.verify(schedulerProvider).ui()
        Mockito.verify(view).renderData(appState)
    }

}