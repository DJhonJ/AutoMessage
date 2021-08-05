package com.automessage.framework.ui.programming

import com.automessage.framework.ui.common.IShowMessage

interface IProgrammingView: IShowMessage {
    override fun show(message: String)
    fun initActivity()
}