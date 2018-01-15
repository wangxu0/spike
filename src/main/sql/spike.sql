DELIMITER $$
CREATE PROCEDURE `spike`.`execute_spike`(IN p_spike_id BIGINT, IN p_phone VARCHAR(50), IN p_spike_time TIMESTAMP, OUT p_ret INT)
  BEGIN
    DECLARE insert_cnt INT DEFAULT 0;
    START TRANSACTION ;
    INSERT IGNORE INTO success_spike(spike_id, user_phone) VALUES (p_spike_id, p_phone);
    SELECT row_count() INTO insert_cnt;
    IF (insert_cnt = 0) THEN
      ROLLBACK ;
      SET p_ret = -1;
    ELSEIF (insert_cnt < 0) THEN
      ROLLBACK ;
      SET p_ret = -2;
    ELSE
      UPDATE spike SET number=number-1 WHERE spike_id=p_spike_id AND end_time>p_spike_time AND start_time<p_spike_time AND number>0;
      SELECT row_count() INTO insert_cnt;
      if (insert_cnt = 0) THEN
        ROLLBACK ;
        SET p_ret = 0;
      ELSEIF (insert_cnt < 0) THEN
        ROLLBACK ;
        SET p_ret = -2;
      ELSE
        COMMIT ;
        SET p_ret = 1;
      END IF ;
    END IF;
  END $$
DELIMITER ;