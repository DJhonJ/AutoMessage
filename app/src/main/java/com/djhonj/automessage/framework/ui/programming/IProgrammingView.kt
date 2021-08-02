package com.djhonj.automessage.framework.ui.programming

import com.djhonj.automessage.framework.ui.common.IShowMessage

interface IProgrammingView: IShowMessage {
    override fun show(message: String)
    fun initActivity()
}