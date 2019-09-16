package com.advancewars.server.redis.converter;

import com.advancewars.model.playboard.Unit;
import com.google.common.primitives.Bytes;
import com.google.common.primitives.Longs;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitToStringConverter implements Converter<Unit, byte[]> {
    @Override
    public byte[] convert(Unit unit) {

        byte[] pvByte = Longs.toByteArray(unit.getPv());
        byte[] pmBytes = Longs.toByteArray(unit.getPm());
        byte[] defBytes = Longs.toByteArray(unit.getDef());
        byte[] atkBytes = Longs.toByteArray(unit.getAtk());
        byte[] rangeByte = Longs.toByteArray(unit.getRange());
        return Bytes.concat(pvByte, pmBytes, defBytes, atkBytes, rangeByte);
    }
}
