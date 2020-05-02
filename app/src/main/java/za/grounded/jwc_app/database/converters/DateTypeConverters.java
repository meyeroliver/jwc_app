package za.grounded.jwc_app.database.converters;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateTypeConverters {
    public DateTypeConverters() {
    }

    @TypeConverter
    public static Date toDate(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long toLong(Date value) {
        return value == null ? null : value.getTime();
    }
}
