CREATE TABLE `staff`(
    `no` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50) NOT NULL,
    `age` INT NOT NULL,
    `salary` BIGINT NOT NULL,
    `dept` VARCHAR(50) NULL,
    `teacher_subject` VARCHAR(50) NULL,
    `manage_dept` VARCHAR(50) NULL,
    `type` ENUM('Teacher', 'Admin', 'Janitor') NOT NULL
);