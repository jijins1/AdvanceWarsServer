package com.advancewars.server.redis.converter;

import com.advancewars.model.playboard.Unit;
import com.google.common.primitives.Longs;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class StringToUnitConverter implements Converter<byte[], Unit> {
    @Override
    public Unit convert(byte[] source) {
        List<Long> longs = new ArrayList<>();
        for (int i = 0; i < source.length; i = i + Longs.BYTES) {
            longs.add(Longs.fromByteArray(Arrays.copyOfRange(source, i, i + Longs.BYTES)));
        }
        return new Unit(
                longs.get(0),
                longs.get(1),
                longs.get(2),
                longs.get(3),
                longs.get(4));
    }
}
