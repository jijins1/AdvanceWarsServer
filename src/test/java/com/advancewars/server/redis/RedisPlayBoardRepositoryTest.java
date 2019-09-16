package com.advancewars.server.redis;

import com.advancewars.model.carte.GenerateurDeCarte;
import com.advancewars.model.carte.Point;
import com.advancewars.model.playboard.PlayBoard;
import com.advancewars.model.playboard.Unit;
import com.advancewars.server.redis.entity.repository.PlayBoardRepository;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RedisPlayBoardRepositoryTest {
    @Autowired
    PlayBoardRepository repository;

    @Test
    public void testAdd() {
        PlayBoard playBoard = new PlayBoard(new GenerateurDeCarte(10).withForet().withMontagne().generate());
        playBoard.addUnit(new Point(0, 0), getUnit());
        playBoard.addUnit(new Point(2, 2), getUnitMAX());
        playBoard = repository.save(playBoard);

        assertThat(playBoard).isNotNull();
        PlayBoard result = repository.findById(playBoard.getId()).orElse(null);
        assertThat(result).isNotNull();
        assertThat(result.getUnitPlacement().getValue(2, 2)).isNotNull().isEqualTo(getUnitMAX());
        assertThat(result.getUnitPlacement().getValue(0, 0)).isNotNull().isEqualTo(getUnit());

    }

    private Unit getUnitMAX() {
        return new Unit(9223372036854775807L, 9223372036854775807L, 9223372036854775807L, 9223372036854775807L, 9223372036854775807L);
    }

    private Unit getUnit() {
        return new Unit(10L, 3L, 5L, 6L, 3L);
    }
}
