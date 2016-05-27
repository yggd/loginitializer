package org.yggd.kotlin

import org.apache.commons.logging.LogFactory
import org.apache.log4j.Logger
import org.slf4j.LoggerFactory

/**
 * Obtain SLF4J logger instance.
 * logger will be named by caller java class.
 *
 * @return logger instance of org.slf4j.Logger
 */
fun <T : Any> T.forSlf4j() : org.slf4j.Logger = LoggerFactory.getLogger(this.javaClass)

/**
 * Obtain Apache Commons logging (JCL) logger instance.
 * logger will be named by caller java class.
 *
 * @return logger instance of org.apache.commons.logging.Log
 */
fun <T : Any> T.forJcl() : org.apache.commons.logging.Log = LogFactory.getLog(this.javaClass)

/**
 * Obtain Log4J logger instance.
 * logger will be named by caller java class.
 *
 * @return logger instance of org.apache.log4j.Logger
 */
fun <T : Any> T.forLog4j() = Logger.getLogger(this.javaClass)
