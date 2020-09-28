package com.sample

import kotlinx.coroutines.delay
import kotlin.test.Test
import kotlin.test.assertEquals

class SolutionBonusImplTest {

    private fun createSolutionBonusImpl(): SolutionBonusApi {
        return SolutionBonusImpl(
            solutionAb = alwaysAvailableAbSolution(),
            initState = SolutionBonusImpl.State(1000, true)
        )
    }

    private fun alwaysAvailableAbSolution(): SolutionAbApi {
        return object : SolutionAbApi {
            override fun registerBooleanToggle(key: String, defaultValue: Boolean) {

            }

            override fun getBooleanToggleState(key: String): Boolean {
                return true
            }
        }
    }

    @Test
    fun testCalcDiscount() {
        testCoroutine {
            val solutionBonus = createSolutionBonusImpl()
            delay(10)
            assertEquals(400, solutionBonus.calcDiscount(Ticket(1000, "")))
        }
    }

    @Test
    fun testSpendBonuses() {
        testCoroutine {
            val solutionBonus = createSolutionBonusImpl()
            solutionBonus.spendBonuses(Ticket(1000, ""))
            delay(10)
            assertEquals(600, solutionBonus.getBonusesAmount())
        }
    }

    @Test
    fun testRefundTicket() {
        testCoroutine {
            val solutionBonus = createSolutionBonusImpl()
            solutionBonus.refundTicket(Ticket(1000, ""))
            delay(10)
            assertEquals(1900, solutionBonus.getBonusesAmount())
        }
    }

}
