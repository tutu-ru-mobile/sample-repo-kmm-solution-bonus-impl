package com.sample

import react.RBuilder
import react.dom.br
import react.dom.div

class SolutionBonusBrowserImpl(
    val common: SolutionBonusImpl
) : SolutionBonusBrowserApi {

    override fun renderBonusesAndRefillButton(react: RBuilder) {
        react.apply {
            if (common.isAvailable()) {
                renderBonusCount(this)
                renderRefillButton(this)
            }
        }
    }

    override fun renderBonusCount(react: RBuilder) {
        react.apply {
            if (common.isAvailable()) {
                div {
                    +"У вас ${common.store.stateFlow.value.bonusAmount} бонусов"
                }
            }
        }
    }

    override fun renderBonusToggle(react: RBuilder) {
        react.apply {
            if (common.isAvailable()) {
                checkBox("использовать бонусы", common.canBuyWithBonus()) {
                    common.store.send(SolutionBonusImpl.Action.SwitchBuyToggle())
                }
                if (common.canBuyWithBonus()) {
                    div {
                        renderBonusCount(this)
                        br {}
                        +common.getBonusRules()
                    }
                }

            }
        }
    }

    fun renderRefillButton(react: RBuilder) {
        react.apply {
            if (common.isAvailable()) {
                div {
                    btn("Добавить бонусы") {
                        common.store.send(SolutionBonusImpl.Action.Add(1000))
                    }
                }
            }
        }
    }

}

