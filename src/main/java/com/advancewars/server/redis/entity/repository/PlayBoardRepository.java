package com.advancewars.server.redis.entity.repository;

import com.advancewars.model.playboard.PlayBoard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayBoardRepository extends CrudRepository<PlayBoard, String> {

}
