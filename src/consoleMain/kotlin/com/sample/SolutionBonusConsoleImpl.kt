package com.sample


class SolutionBonusConsoleImpl(
    val common: SolutionBonusImpl
) : SolutionBonusConsoleApi {

    override fun renderBonusesAndRefillButton(builder: ConsolePanelBuilder) {
        builder.apply {
            if (common.isAvailable()) {
                renderBonusCount(this)
                button("Добавить бонусы") {
                    common.store.send(SolutionBonusImpl.Action.Add(1000))
                }
            }
        }
    }

    override fun renderBonusCount(builder: ConsolePanelBuilder) {
        builder.apply {
            if (common.isAvailable()) {
                label("У вас ${common.store.stateFlow.value.bonusAmount} бонусов")
            }
        }
    }

    override fun renderBonusToggle(builder: ConsolePanelBuilder) {
        builder.apply {
            if (common.isAvailable()) {
                checkBox("использовать бонусы", common.canBuyWithBonus()) {
                    common.store.send(SolutionBonusImpl.Action.SwitchBuyToggle())
                }
                if (common.canBuyWithBonus()) {
                    renderBonusCount(this)
                    label(common.getBonusRules())
                }
            }
        }
    }

}

