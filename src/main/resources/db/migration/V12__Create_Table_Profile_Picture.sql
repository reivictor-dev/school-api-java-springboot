CREATE TABLE IF NOT EXISTS profile_picture (
  id bigint(20) NOT NULL AUTO_INCREMENT,
    file_name VARCHAR(255),
    file_type VARCHAR(255),
    data LONGBLOB,
    student_id bigint(20) UNIQUE,
    PRIMARY KEY (`id`),
    CONSTRAINT fk_profile_picture FOREIGN KEY (student_id) REFERENCES student (id)
);