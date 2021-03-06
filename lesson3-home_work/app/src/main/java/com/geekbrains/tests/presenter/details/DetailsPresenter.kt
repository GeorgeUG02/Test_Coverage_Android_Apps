package com.geekbrains.tests.presenter.details

import com.geekbrains.tests.view.ViewContract
import com.geekbrains.tests.view.details.ViewDetailsContract
import com.geekbrains.tests.view.search.ViewSearchContract

internal class DetailsPresenter internal constructor(
    private var count: Int = 0
) : PresenterDetailsContract {
    private var viewContract: ViewDetailsContract?=null

    override fun setCounter(count: Int) {
        this.count = count
    }

    override fun onIncrement() {
        count++
        viewContract?.setCount(count)
    }

    override fun onDecrement() {
        count--
        viewContract?.setCount(count)
    }

    override fun onAttach(viewContract: ViewContract) {
        if (viewContract != this.viewContract) {
            this.viewContract=viewContract as ViewDetailsContract
        }
    }

    override fun onDetach(viewContract: ViewContract) {
        if (viewContract as ViewDetailsContract == this.viewContract) {
            this.viewContract = null
        }
    }
}
