package org.yggd.kotlin

import org.apache.commons.logging.impl.SimpleLog
import org.apache.log4j.Appender
import org.apache.log4j.Level
import org.apache.log4j.LogManager
import org.apache.log4j.spi.LoggingEvent
import org.hamcrest.CoreMatchers.*
import org.jetbrains.spek.api.DescribeParser
import org.jetbrains.spek.api.Spek
import org.junit.Assert.*
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.*
import uk.org.lidalia.slf4jtest.LoggingEvent.*
import uk.org.lidalia.slf4jtest.TestLoggerFactory
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class LogInitializerTest : Spek({
    given("some logger test") {
        on("test for slf4j") {
            val testLogger = TestLoggerFactory.getTestLogger(DescribeParser::class.java)
            val loggerSlf4j = forSlf4j()

            loggerSlf4j.warn("test slf4j")

            assertThat(testLogger.loggingEvents, `is`(listOf(warn("test slf4j"))))
        }
        on("test for jcl") {
            val logJcl = forJcl() as SimpleLog

            val errOrg = System.err
            val errTest = ByteArrayOutputStream()
            // stderr replace
            System.setErr(PrintStream(errTest))
            try {
               logJcl.error("test jcl")

                assertThat(errTest.toString(System.getProperty("file.encoding")),
                        containsString("[ERROR] DescribeParser - test jcl"))
            } finally {
                System.setErr(errOrg)
                errTest.close()
            }

        }
        on("test for log4j") {
            val captor = ArgumentCaptor.forClass(LoggingEvent::class.java)
            val appender = mock(Appender::class.java)
            LogManager.getRootLogger().addAppender(appender)

            val loggerlog4j = forLog4j()
            loggerlog4j.info("test log4j")

            verify(appender).doAppend(captor.capture())
            val event = captor.value
            assertThat(event.getLevel(), `is`(Level.INFO))
            assertThat(event.renderedMessage, `is`("test log4j"))
        }
    }
})
