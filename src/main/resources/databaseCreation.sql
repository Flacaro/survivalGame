CREATE TABLE crafting_catalog (
    id BIGINT PRIMARY KEY,
    id_resource_final BIGINT,
    id_resources_to_craft VARCHAR(255)
);


INSERT INTO `the_survival_game`.`resource`(`id`,`name`,`type`,`CATEGORY`,`LEVEL`)
VALUES("pietra","arma","arma",1);

INSERT INTO `the_survival_game`.`resource`(`id`,`name`,`type`,`CATEGORY`,`LEVEL`)
VALUES("legno","arma","arma",1);

INSERT INTO `the_survival_game`.`resource`(`id`,`name`,`type`,`CATEGORY`,`LEVEL`)
VALUES("lancia","arma","arma",3);

INSERT INTO `the_survival_game`.`crafting_catalog`(`id`,`FINAL_RESOURCE`,`ID_RESOURCES_TO_CRAFT`)
VALUES(<{id: }>,<{FINAL_RESOURCE: }>,<{ID_RESOURCES_TO_CRAFT: }>);