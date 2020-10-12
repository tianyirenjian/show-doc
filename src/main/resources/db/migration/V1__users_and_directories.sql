DROP TABLE IF EXISTS `directories`;
CREATE TABLE `directories`
(
    `id`         int                                                           NOT NULL AUTO_INCREMENT,
    `name`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    `pid`        int      DEFAULT NULL,
    `created_at` datetime DEFAULT NULL,
    `updated_at` datetime DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;


DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`
(
    `id`           int                                     NOT NULL AUTO_INCREMENT,
    `username`     varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    `password`     varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    `enabled`      bit(1)                                  NOT NULL DEFAULT b'1',
    `active_until` datetime                                         DEFAULT NULL,
    `created_at`   datetime                                         DEFAULT NULL,
    `updated_at`   datetime                                         DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;