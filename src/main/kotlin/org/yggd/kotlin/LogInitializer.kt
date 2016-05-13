package org.yggd.kotlin

import org.apache.commons.logging.LogFactory
import org.apache.log4j.Logger
import org.slf4j.LoggerFactory

/*
 * Log Initializer
 */

fun <T : Any> T.forSlf4j() : org.slf4j.Logger = LoggerFactory.getLogger(this.javaClass)

fun <T : Any> T.forJcl() : org.apache.commons.logging.Log = LogFactory.getLog(this.javaClass)

fun <T : Any> T.forLog4j() = Logger.getLogger(this.javaClass)
