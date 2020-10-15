ALTER TABLE `directories`
    CHANGE `pid` `pid` int NOT NULL DEFAULT '0' AFTER `name`;