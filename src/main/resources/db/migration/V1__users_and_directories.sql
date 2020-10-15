DROP TABLE IF EXISTS `directories`;
CREATE TABLE `directories`
(
    `id`         int                                                           NOT NULL AUTO_INCREMENT,
    `name`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    `pid`        int                                                           NOT NULL DEFAULT '0',
    `created_at` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;


DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`
(
    `id`           int                                                           NOT NULL AUTO_INCREMENT,
    `username`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    `password`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    `enabled`      bit(1)                                                        NOT NULL DEFAULT b'1',
    `active_until` datetime                                                      NOT NULL DEFAULT '9999-01-01 00:00:00',
    `created_at`   datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`   datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `admin`        bit(1)                                                                 DEFAULT b'0',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
