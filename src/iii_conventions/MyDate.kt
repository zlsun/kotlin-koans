package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate) = when {
        year != other.year -> year - other.year
        month != other.month -> month - other.month
        else -> dayOfMonth - other.dayOfMonth
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(
        override val start: MyDate,
        override val endInclusive: MyDate
) : ClosedRange<MyDate>, Iterable<MyDate> {
    override fun contains(item: MyDate) = start <= item && item <= endInclusive
    override fun iterator(): Iterator<MyDate> = DateIterator(this)
}

class DateIterator(val dateRange: DateRange) : Iterator<MyDate> {
    var current: MyDate = dateRange.start
    override fun next(): MyDate {
        val result = current
        current = current.addTimeIntervals(TimeInterval.DAY, 1)
        return result
    }
    override fun hasNext(): Boolean = current <= dateRange.endInclusive
}

class RepeatedTimeInterval(val ti: TimeInterval, val n: Int)
operator fun TimeInterval.times(number: Int) = RepeatedTimeInterval(this, number)

operator fun MyDate.plus(interval: TimeInterval) = addTimeIntervals(interval, 1)
operator fun MyDate.plus(intervals: RepeatedTimeInterval) = addTimeIntervals(intervals.ti, intervals.n)
