package schwarz.it.lws.dateTimeUtils

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

class DateTimeUtilsTest : FunSpec({


    test("isOlderThanThreeHours") {
        DateTimeUtils.isOlderThanThreeHours(LocalDateTime.now().minusHours(4)) shouldBe true

        DateTimeUtils.isOlderThanThreeHours(LocalDateTime.now().minusHours(2)) shouldBe false
    }

})
