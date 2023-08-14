CREATE TABLE IF NOT EXISTS grade (
  id SERIAL PRIMARY KEY,
    math DOUBLE PRECISION,
    chemistry DOUBLE PRECISION,
    science DOUBLE PRECISION,
    english DOUBLE PRECISION,
    student_id BIGINT,
    CONSTRAINT fk_grade FOREIGN KEY (student_id) REFERENCES student (id)
);