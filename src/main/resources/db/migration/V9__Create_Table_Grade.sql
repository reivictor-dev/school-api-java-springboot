CREATE TABLE IF NOT EXISTS grade (
  id bigint(20) NOT NULL AUTO_INCREMENT,
    math DOUBLE PRECISION,
    chemistry DOUBLE PRECISION,
    science DOUBLE PRECISION,
    english DOUBLE PRECISION,
    student_id BIGINT,
    PRIMARY KEY (`id`),
    CONSTRAINT fk_grade FOREIGN KEY (student_id) REFERENCES student (id)
) ENGINE=InnoDB;