package com.dmt.wordlearner.utils

import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.temporal.TemporalAdjusters

private val DEFAULT_ZONE_ID = ZoneId.systemDefault()

fun startOfDay(): LocalDateTime =
    LocalDateTime.now(DEFAULT_ZONE_ID).with(LocalTime.MIN)

fun endOfDay(): LocalDateTime =
    LocalDateTime.now(DEFAULT_ZONE_ID).with(LocalTime.MAX)

fun belongsToCurrentDay(localDateTime: LocalDateTime): Boolean =
    localDateTime.isAfter(startOfDay()) && localDateTime.isBefore(
        endOfDay()
    )



//note that week starts with Monday
fun startOfWeek(): LocalDateTime =
    LocalDateTime.now(DEFAULT_ZONE_ID).with(LocalTime.MIN)
        .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))

//note that week ends with Sunday
fun endOfWeek(): LocalDateTime =
    LocalDateTime.now(DEFAULT_ZONE_ID).with(LocalTime.MAX)
        .with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))

fun belongsToCurrentWeek(localDateTime: LocalDateTime): Boolean =
    localDateTime.isAfter(startOfWeek()) && localDateTime.isBefore(
        endOfWeek()
    )



fun startOfMonth(): LocalDateTime =
    LocalDateTime.now(DEFAULT_ZONE_ID).with(LocalTime.MIN)
        .with(TemporalAdjusters.firstDayOfMonth())

fun endOfMonth(): LocalDateTime =
    LocalDateTime.now(DEFAULT_ZONE_ID).with(LocalTime.MAX)
        .with(TemporalAdjusters.lastDayOfMonth())

fun belongsToCurrentMonth(localDateTime: LocalDateTime): Boolean =
    localDateTime.isAfter(startOfMonth()) && localDateTime.isBefore(
        endOfMonth()
    )



fun startOfYear(): LocalDateTime =
    LocalDateTime.now(DEFAULT_ZONE_ID).with(LocalTime.MIN)
        .with(TemporalAdjusters.firstDayOfYear())

fun endOfYear(): LocalDateTime =
    LocalDateTime.now(DEFAULT_ZONE_ID).with(LocalTime.MAX)
        .with(TemporalAdjusters.lastDayOfYear())

fun belongsToCurrentYear(localDateTime: LocalDateTime): Boolean =
    localDateTime.isAfter(startOfYear()) && localDateTime.isBefore(
        endOfYear()
    )