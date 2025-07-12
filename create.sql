
    create table `so_hu` (
        `ND_Id` integer not null,
        `VT_Id` integer not null,
        `nd_id` integer not null,
        `vt_id` integer not null,
        primary key (`ND_Id`, `VT_Id`)
    ) engine=InnoDB;

    create table `vi_tien` (
        `lv_id` integer not null,
        `tt_id` integer not null,
        `vt_id` integer not null auto_increment,
        `vt_so_du` decimal(10,2) not null,
        primary key (`vt_id`)
    ) engine=InnoDB;

    create table `chuc_nang` (
        `cn_id` integer not null auto_increment,
        `cn_ten` varchar(256) not null,
        primary key (`cn_id`)
    ) engine=InnoDB;

    create table `chuc_nang_quyen_truy_caps` (
        `chuc_nang_cn_id` integer not null,
        `quyen_truy_caps_cn_id` integer not null,
        primary key (`chuc_nang_cn_id`, `quyen_truy_caps_cn_id`)
    ) engine=InnoDB;

    create table `han_muc_chi` (
        `LKC_Id` integer not null,
        `Ngay_Id` integer not null,
        `VT_Id` integer not null,
        `hmc_so_tien` decimal(10,2) not null,
        `lkc_id` integer not null,
        `ngay_id` integer not null,
        `tt_id` integer not null,
        `vt_id` integer not null,
        `hmc_mo_ta` tinytext,
        primary key (`LKC_Id`, `Ngay_Id`, `VT_Id`)
    ) engine=InnoDB;

    create table `khoan_chi` (
        `kc_id` integer not null auto_increment,
        `kc_lap_lai` bit not null,
        `kc_ngay_ket_thuc_lap_lai` date not null,
        `kc_so_tien` decimal(10,2) not null,
        `lkc_id` integer not null,
        `nd_id` integer not null,
        `ngay_id` integer not null,
        `pttt_id` integer not null,
        `vt_id` integer not null,
        `kc_mo_ta` tinytext,
        primary key (`kc_id`)
    ) engine=InnoDB;

    create table `khoan_thu` (
        `kt_id` integer not null auto_increment,
        `kt_so_tien` decimal(10,2) not null,
        `lkt_id` integer not null,
        `nd_id` integer not null,
        `ngay_id` integer not null,
        `vt_id` integer not null,
        primary key (`kt_id`)
    ) engine=InnoDB;

    create table `loai_khoan_chi` (
        `lkc_id` integer not null auto_increment,
        `lkc_ten` varchar(255) not null,
        `lkc_mo_ta` tinytext,
        primary key (`lkc_id`)
    ) engine=InnoDB;

    create table `loai_khoan_thu` (
        `lkt_id` integer not null,
        `lkt_ten` varchar(256) not null,
        primary key (`lkt_id`)
    ) engine=InnoDB;

    create table `loai_vi` (
        `lv_id` integer not null auto_increment,
        `lv_ten` varchar(256) not null,
        primary key (`lv_id`)
    ) engine=InnoDB;

    create table `ngay` (
        `ngay_id` integer not null auto_increment,
        `ngay_trong_nam` integer not null,
        `ngay_trong_thang` integer not null,
        `ngay_trong_tuan` integer not null,
        `ngay_daydu` datetime(6),
        primary key (`ngay_id`)
    ) engine=InnoDB;

    create table `nguoi_dung` (
        `nd_id` integer not null auto_increment,
        `pq_id` integer,
        `nd_ngay_tao` datetime(6) default CURRENT_TIMESTAMP,
        `nd_avatar` varchar(10),
        `nd_trang_thai` varchar(50) not null,
        `nd_email` varchar(255),
        `nd_mat_khau` varchar(255) not null,
        `nd_ten` varchar(255) not null,
        primary key (`nd_id`)
    ) engine=InnoDB;

    create table `phan_quyen` (
        `pq_cap_do` integer not null,
        `pq_id` integer not null auto_increment,
        `pq_ten` varchar(256) not null,
        primary key (`pq_id`)
    ) engine=InnoDB;

    create table `phuong_thuc_thanh_toan` (
        `pttt_id` integer not null auto_increment,
        `pttt_loai` varchar(100) not null,
        `pttt_so_tk` tinytext,
        primary key (`pttt_id`)
    ) engine=InnoDB;

    create table `quyen_truy_cap` (
        `cn_id` integer not null,
        `pq_id` integer,
        `qtc_cho_phep` bit not null,
        primary key (`cn_id`)
    ) engine=InnoDB;

    create table `trang_thai` (
        `tt_id` integer not null auto_increment,
        `tt_ten` varchar(256) not null,
        `tt_mota` tinytext not null,
        primary key (`tt_id`)
    ) engine=InnoDB;

    alter table `chuc_nang_quyen_truy_caps` 
       add constraint `UK1m3xjacixiwmwo3j7u9d8dh5n` unique (`quyen_truy_caps_cn_id`);

    alter table `so_hu` 
       add constraint `FK76re6ogdllmku4072wq26v56i` 
       foreign key (`nd_id`) 
       references `nguoi_dung` (`nd_id`);

    alter table `so_hu` 
       add constraint `FKepcd0f9kbvpyad4vow4kud5qv` 
       foreign key (`vt_id`) 
       references `vi_tien` (`vt_id`);

    alter table `vi_tien` 
       add constraint `FKrmrwq1ism31a35g2qqtuqnbyk` 
       foreign key (`lv_id`) 
       references `loai_vi` (`lv_id`);

    alter table `vi_tien` 
       add constraint `FKhvj3f0a7ajd2kmvnvsi3tnelh` 
       foreign key (`tt_id`) 
       references `trang_thai` (`tt_id`);

    alter table `chuc_nang_quyen_truy_caps` 
       add constraint `FK6mc9yb29a37nippkgr8u5oeqf` 
       foreign key (`quyen_truy_caps_cn_id`) 
       references `quyen_truy_cap` (`cn_id`);

    alter table `chuc_nang_quyen_truy_caps` 
       add constraint `FK3v1582tmmfii6wilbhbb0wwov` 
       foreign key (`chuc_nang_cn_id`) 
       references `chuc_nang` (`cn_id`);

    alter table `han_muc_chi` 
       add constraint `FK7e5nxi1vs3mtxolwjk63naqax` 
       foreign key (`lkc_id`) 
       references `loai_khoan_chi` (`lkc_id`);

    alter table `han_muc_chi` 
       add constraint `FKo7492u5a6x547o5tfhfdnfqwq` 
       foreign key (`ngay_id`) 
       references `ngay` (`ngay_id`);

    alter table `han_muc_chi` 
       add constraint `FKkiravinknsh9kaoxc43ksutrd` 
       foreign key (`tt_id`) 
       references `trang_thai` (`tt_id`);

    alter table `han_muc_chi` 
       add constraint `FKeytel4slec66i0uqqrfpegg7h` 
       foreign key (`vt_id`) 
       references `vi_tien` (`vt_id`);

    alter table `khoan_chi` 
       add constraint `FKg4wewtsqgy0k2jo788w6bkjfn` 
       foreign key (`lkc_id`) 
       references `loai_khoan_chi` (`lkc_id`);

    alter table `khoan_chi` 
       add constraint `FKgthhfiy258xa7407tmnmyx90` 
       foreign key (`nd_id`) 
       references `nguoi_dung` (`nd_id`);

    alter table `khoan_chi` 
       add constraint `FKiw186g4r7q9ah3pmxx6pfmyqb` 
       foreign key (`ngay_id`) 
       references `ngay` (`ngay_id`);

    alter table `khoan_chi` 
       add constraint `FK6uq8fsfwuhae2nelrpck5tder` 
       foreign key (`pttt_id`) 
       references `phuong_thuc_thanh_toan` (`pttt_id`);

    alter table `khoan_chi` 
       add constraint `FK4i1rpjeidadllsu83qnc1olt6` 
       foreign key (`vt_id`) 
       references `vi_tien` (`vt_id`);

    alter table `khoan_thu` 
       add constraint `FK7iw6xliyqcqr1651u1b3tbo1b` 
       foreign key (`lkt_id`) 
       references `loai_khoan_thu` (`lkt_id`);

    alter table `khoan_thu` 
       add constraint `FKmw58v68fh7vh57ak6wwtne0yo` 
       foreign key (`nd_id`) 
       references `nguoi_dung` (`nd_id`);

    alter table `khoan_thu` 
       add constraint `FKmcym9xyoejj20nie2mpbjmxu7` 
       foreign key (`ngay_id`) 
       references `ngay` (`ngay_id`);

    alter table `khoan_thu` 
       add constraint `FKt1cjgq2yv3dujbsw3kl4xjt49` 
       foreign key (`vt_id`) 
       references `vi_tien` (`vt_id`);

    alter table `nguoi_dung` 
       add constraint `FKqxftvm80i2pwm2rsjp9nrktbs` 
       foreign key (`pq_id`) 
       references `phan_quyen` (`pq_id`);

    alter table `quyen_truy_cap` 
       add constraint `FKdycooh5vvx5mqwt4dk62g5737` 
       foreign key (`cn_id`) 
       references `chuc_nang` (`cn_id`);

    alter table `quyen_truy_cap` 
       add constraint `FK158o5s4h1dol05fo8c6wbslh7` 
       foreign key (`pq_id`) 
       references `phan_quyen` (`pq_id`);

    create table `so_hu` (
        `ND_Id` integer not null,
        `VT_Id` integer not null,
        `nd_id` integer not null,
        `vt_id` integer not null,
        primary key (`ND_Id`, `VT_Id`)
    ) engine=InnoDB;

    create table `vi_tien` (
        `lv_id` integer not null,
        `tt_id` integer not null,
        `vt_id` integer not null auto_increment,
        `vt_so_du` decimal(10,2) not null,
        primary key (`vt_id`)
    ) engine=InnoDB;

    create table `chuc_nang` (
        `cn_id` integer not null auto_increment,
        `cn_ten` varchar(256) not null,
        primary key (`cn_id`)
    ) engine=InnoDB;

    create table `chuc_nang_quyen_truy_caps` (
        `chuc_nang_cn_id` integer not null,
        `quyen_truy_caps_cn_id` integer not null,
        primary key (`chuc_nang_cn_id`, `quyen_truy_caps_cn_id`)
    ) engine=InnoDB;

    create table `han_muc_chi` (
        `LKC_Id` integer not null,
        `Ngay_Id` integer not null,
        `VT_Id` integer not null,
        `hmc_so_tien` decimal(10,2) not null,
        `lkc_id` integer not null,
        `ngay_id` integer not null,
        `tt_id` integer not null,
        `vt_id` integer not null,
        `hmc_mo_ta` tinytext,
        primary key (`LKC_Id`, `Ngay_Id`, `VT_Id`)
    ) engine=InnoDB;

    create table `khoan_chi` (
        `kc_id` integer not null auto_increment,
        `kc_lap_lai` bit not null,
        `kc_ngay_ket_thuc_lap_lai` date not null,
        `kc_so_tien` decimal(10,2) not null,
        `lkc_id` integer not null,
        `nd_id` integer not null,
        `ngay_id` integer not null,
        `pttt_id` integer not null,
        `vt_id` integer not null,
        `kc_mo_ta` tinytext,
        primary key (`kc_id`)
    ) engine=InnoDB;

    create table `khoan_thu` (
        `kt_id` integer not null auto_increment,
        `kt_so_tien` decimal(10,2) not null,
        `lkt_id` integer not null,
        `nd_id` integer not null,
        `ngay_id` integer not null,
        `vt_id` integer not null,
        primary key (`kt_id`)
    ) engine=InnoDB;

    create table `loai_khoan_chi` (
        `lkc_id` integer not null auto_increment,
        `lkc_ten` varchar(255) not null,
        `lkc_mo_ta` tinytext,
        primary key (`lkc_id`)
    ) engine=InnoDB;

    create table `loai_khoan_thu` (
        `lkt_id` integer not null,
        `lkt_ten` varchar(256) not null,
        primary key (`lkt_id`)
    ) engine=InnoDB;

    create table `loai_vi` (
        `lv_id` integer not null auto_increment,
        `lv_ten` varchar(256) not null,
        primary key (`lv_id`)
    ) engine=InnoDB;

    create table `ngay` (
        `ngay_id` integer not null auto_increment,
        `ngay_trong_nam` integer not null,
        `ngay_trong_thang` integer not null,
        `ngay_trong_tuan` integer not null,
        `ngay_daydu` datetime(6),
        primary key (`ngay_id`)
    ) engine=InnoDB;

    create table `nguoi_dung` (
        `nd_id` integer not null auto_increment,
        `pq_id` integer,
        `nd_ngay_tao` datetime(6) default CURRENT_TIMESTAMP,
        `nd_avatar` varchar(10),
        `nd_trang_thai` varchar(50) not null,
        `nd_email` varchar(255),
        `nd_mat_khau` varchar(255) not null,
        `nd_ten` varchar(255) not null,
        primary key (`nd_id`)
    ) engine=InnoDB;

    create table `phan_quyen` (
        `pq_cap_do` integer not null,
        `pq_id` integer not null auto_increment,
        `pq_ten` varchar(256) not null,
        primary key (`pq_id`)
    ) engine=InnoDB;

    create table `phuong_thuc_thanh_toan` (
        `pttt_id` integer not null auto_increment,
        `pttt_loai` varchar(100) not null,
        `pttt_so_tk` tinytext,
        primary key (`pttt_id`)
    ) engine=InnoDB;

    create table `quyen_truy_cap` (
        `cn_id` integer not null,
        `pq_id` integer,
        `qtc_cho_phep` bit not null,
        primary key (`cn_id`)
    ) engine=InnoDB;

    create table `trang_thai` (
        `tt_id` integer not null auto_increment,
        `tt_ten` varchar(256) not null,
        `tt_mota` tinytext not null,
        primary key (`tt_id`)
    ) engine=InnoDB;

    alter table `chuc_nang_quyen_truy_caps` 
       add constraint `UK1m3xjacixiwmwo3j7u9d8dh5n` unique (`quyen_truy_caps_cn_id`);

    alter table `so_hu` 
       add constraint `FK76re6ogdllmku4072wq26v56i` 
       foreign key (`nd_id`) 
       references `nguoi_dung` (`nd_id`);

    alter table `so_hu` 
       add constraint `FKepcd0f9kbvpyad4vow4kud5qv` 
       foreign key (`vt_id`) 
       references `vi_tien` (`vt_id`);

    alter table `vi_tien` 
       add constraint `FKrmrwq1ism31a35g2qqtuqnbyk` 
       foreign key (`lv_id`) 
       references `loai_vi` (`lv_id`);

    alter table `vi_tien` 
       add constraint `FKhvj3f0a7ajd2kmvnvsi3tnelh` 
       foreign key (`tt_id`) 
       references `trang_thai` (`tt_id`);

    alter table `chuc_nang_quyen_truy_caps` 
       add constraint `FK6mc9yb29a37nippkgr8u5oeqf` 
       foreign key (`quyen_truy_caps_cn_id`) 
       references `quyen_truy_cap` (`cn_id`);

    alter table `chuc_nang_quyen_truy_caps` 
       add constraint `FK3v1582tmmfii6wilbhbb0wwov` 
       foreign key (`chuc_nang_cn_id`) 
       references `chuc_nang` (`cn_id`);

    alter table `han_muc_chi` 
       add constraint `FK7e5nxi1vs3mtxolwjk63naqax` 
       foreign key (`lkc_id`) 
       references `loai_khoan_chi` (`lkc_id`);

    alter table `han_muc_chi` 
       add constraint `FKo7492u5a6x547o5tfhfdnfqwq` 
       foreign key (`ngay_id`) 
       references `ngay` (`ngay_id`);

    alter table `han_muc_chi` 
       add constraint `FKkiravinknsh9kaoxc43ksutrd` 
       foreign key (`tt_id`) 
       references `trang_thai` (`tt_id`);

    alter table `han_muc_chi` 
       add constraint `FKeytel4slec66i0uqqrfpegg7h` 
       foreign key (`vt_id`) 
       references `vi_tien` (`vt_id`);

    alter table `khoan_chi` 
       add constraint `FKg4wewtsqgy0k2jo788w6bkjfn` 
       foreign key (`lkc_id`) 
       references `loai_khoan_chi` (`lkc_id`);

    alter table `khoan_chi` 
       add constraint `FKgthhfiy258xa7407tmnmyx90` 
       foreign key (`nd_id`) 
       references `nguoi_dung` (`nd_id`);

    alter table `khoan_chi` 
       add constraint `FKiw186g4r7q9ah3pmxx6pfmyqb` 
       foreign key (`ngay_id`) 
       references `ngay` (`ngay_id`);

    alter table `khoan_chi` 
       add constraint `FK6uq8fsfwuhae2nelrpck5tder` 
       foreign key (`pttt_id`) 
       references `phuong_thuc_thanh_toan` (`pttt_id`);

    alter table `khoan_chi` 
       add constraint `FK4i1rpjeidadllsu83qnc1olt6` 
       foreign key (`vt_id`) 
       references `vi_tien` (`vt_id`);

    alter table `khoan_thu` 
       add constraint `FK7iw6xliyqcqr1651u1b3tbo1b` 
       foreign key (`lkt_id`) 
       references `loai_khoan_thu` (`lkt_id`);

    alter table `khoan_thu` 
       add constraint `FKmw58v68fh7vh57ak6wwtne0yo` 
       foreign key (`nd_id`) 
       references `nguoi_dung` (`nd_id`);

    alter table `khoan_thu` 
       add constraint `FKmcym9xyoejj20nie2mpbjmxu7` 
       foreign key (`ngay_id`) 
       references `ngay` (`ngay_id`);

    alter table `khoan_thu` 
       add constraint `FKt1cjgq2yv3dujbsw3kl4xjt49` 
       foreign key (`vt_id`) 
       references `vi_tien` (`vt_id`);

    alter table `nguoi_dung` 
       add constraint `FKqxftvm80i2pwm2rsjp9nrktbs` 
       foreign key (`pq_id`) 
       references `phan_quyen` (`pq_id`);

    alter table `quyen_truy_cap` 
       add constraint `FKdycooh5vvx5mqwt4dk62g5737` 
       foreign key (`cn_id`) 
       references `chuc_nang` (`cn_id`);

    alter table `quyen_truy_cap` 
       add constraint `FK158o5s4h1dol05fo8c6wbslh7` 
       foreign key (`pq_id`) 
       references `phan_quyen` (`pq_id`);

    create table `vi_tien` (
        `lv_id` integer not null,
        `tt_id` integer not null,
        `vt_id` integer not null auto_increment,
        `vt_so_du` decimal(10,2) not null,
        primary key (`vt_id`)
    ) engine=InnoDB;

    create table `chuc_nang` (
        `cn_id` integer not null auto_increment,
        `cn_ten` varchar(256) not null,
        primary key (`cn_id`)
    ) engine=InnoDB;

    create table `chuc_nang_quyen_truy_caps` (
        `chuc_nang_cn_id` integer not null,
        `quyen_truy_caps_cn_id` integer not null,
        primary key (`chuc_nang_cn_id`, `quyen_truy_caps_cn_id`)
    ) engine=InnoDB;

    create table `han_muc_chi` (
        `LKC_Id` integer not null,
        `Ngay_Id` integer not null,
        `VT_Id` integer not null,
        `hmc_so_tien` decimal(10,2) not null,
        `lkc_id` integer not null,
        `ngay_id` integer not null,
        `tt_id` integer not null,
        `vt_id` integer not null,
        `hmc_mo_ta` tinytext,
        primary key (`LKC_Id`, `Ngay_Id`, `VT_Id`)
    ) engine=InnoDB;

    create table `khoan_chi` (
        `kc_id` integer not null auto_increment,
        `kc_lap_lai` bit not null,
        `kc_ngay_ket_thuc_lap_lai` date not null,
        `kc_so_tien` decimal(10,2) not null,
        `lkc_id` integer not null,
        `nd_id` integer not null,
        `ngay_id` integer not null,
        `pttt_id` integer not null,
        `vt_id` integer not null,
        `kc_mo_ta` tinytext,
        primary key (`kc_id`)
    ) engine=InnoDB;

    create table `khoan_thu` (
        `kt_id` integer not null auto_increment,
        `kt_so_tien` decimal(10,2) not null,
        `lkt_id` integer not null,
        `nd_id` integer not null,
        `ngay_id` integer not null,
        `vt_id` integer not null,
        primary key (`kt_id`)
    ) engine=InnoDB;

    create table `loai_khoan_chi` (
        `lkc_id` integer not null auto_increment,
        `lkc_ten` varchar(255) not null,
        `lkc_mo_ta` tinytext,
        primary key (`lkc_id`)
    ) engine=InnoDB;

    create table `loai_khoan_thu` (
        `lkt_id` integer not null,
        `lkt_ten` varchar(256) not null,
        primary key (`lkt_id`)
    ) engine=InnoDB;

    create table `loai_vi` (
        `lv_id` integer not null auto_increment,
        `lv_ten` varchar(256) not null,
        primary key (`lv_id`)
    ) engine=InnoDB;

    create table `ngay` (
        `ngay_id` integer not null auto_increment,
        `ngay_trong_nam` integer not null,
        `ngay_trong_thang` integer not null,
        `ngay_trong_tuan` integer not null,
        `ngay_daydu` datetime(6),
        primary key (`ngay_id`)
    ) engine=InnoDB;

    create table `nguoi_dung` (
        `nd_id` integer not null auto_increment,
        `pq_id` integer,
        `nd_ngay_tao` datetime(6) default CURRENT_TIMESTAMP,
        `nd_avatar` varchar(10),
        `nd_trang_thai` varchar(50) not null,
        `nd_email` varchar(255),
        `nd_mat_khau` varchar(255) not null,
        `nd_ten` varchar(255) not null,
        primary key (`nd_id`)
    ) engine=InnoDB;

    create table `phan_quyen` (
        `pq_cap_do` integer not null,
        `pq_id` integer not null auto_increment,
        `pq_ten` varchar(256) not null,
        primary key (`pq_id`)
    ) engine=InnoDB;

    create table `phuong_thuc_thanh_toan` (
        `pttt_id` integer not null auto_increment,
        `pttt_loai` varchar(100) not null,
        `pttt_so_tk` tinytext,
        primary key (`pttt_id`)
    ) engine=InnoDB;

    create table `quyen_truy_cap` (
        `cn_id` integer not null,
        `pq_id` integer,
        `qtc_cho_phep` bit not null,
        primary key (`cn_id`)
    ) engine=InnoDB;

    create table `so_hu` (
        `nd_id` integer not null,
        `vt_id` integer not null,
        primary key (`nd_id`, `vt_id`)
    ) engine=InnoDB;

    create table `trang_thai` (
        `tt_id` integer not null auto_increment,
        `tt_ten` varchar(256) not null,
        `tt_mota` tinytext not null,
        primary key (`tt_id`)
    ) engine=InnoDB;

    alter table `chuc_nang_quyen_truy_caps` 
       add constraint `UK1m3xjacixiwmwo3j7u9d8dh5n` unique (`quyen_truy_caps_cn_id`);

    alter table `vi_tien` 
       add constraint `FKrmrwq1ism31a35g2qqtuqnbyk` 
       foreign key (`lv_id`) 
       references `loai_vi` (`lv_id`);

    alter table `vi_tien` 
       add constraint `FKhvj3f0a7ajd2kmvnvsi3tnelh` 
       foreign key (`tt_id`) 
       references `trang_thai` (`tt_id`);

    alter table `chuc_nang_quyen_truy_caps` 
       add constraint `FK6mc9yb29a37nippkgr8u5oeqf` 
       foreign key (`quyen_truy_caps_cn_id`) 
       references `quyen_truy_cap` (`cn_id`);

    alter table `chuc_nang_quyen_truy_caps` 
       add constraint `FK3v1582tmmfii6wilbhbb0wwov` 
       foreign key (`chuc_nang_cn_id`) 
       references `chuc_nang` (`cn_id`);

    alter table `han_muc_chi` 
       add constraint `FK7e5nxi1vs3mtxolwjk63naqax` 
       foreign key (`lkc_id`) 
       references `loai_khoan_chi` (`lkc_id`);

    alter table `han_muc_chi` 
       add constraint `FKo7492u5a6x547o5tfhfdnfqwq` 
       foreign key (`ngay_id`) 
       references `ngay` (`ngay_id`);

    alter table `han_muc_chi` 
       add constraint `FKkiravinknsh9kaoxc43ksutrd` 
       foreign key (`tt_id`) 
       references `trang_thai` (`tt_id`);

    alter table `han_muc_chi` 
       add constraint `FKeytel4slec66i0uqqrfpegg7h` 
       foreign key (`vt_id`) 
       references `vi_tien` (`vt_id`);

    alter table `khoan_chi` 
       add constraint `FKg4wewtsqgy0k2jo788w6bkjfn` 
       foreign key (`lkc_id`) 
       references `loai_khoan_chi` (`lkc_id`);

    alter table `khoan_chi` 
       add constraint `FKgthhfiy258xa7407tmnmyx90` 
       foreign key (`nd_id`) 
       references `nguoi_dung` (`nd_id`);

    alter table `khoan_chi` 
       add constraint `FKiw186g4r7q9ah3pmxx6pfmyqb` 
       foreign key (`ngay_id`) 
       references `ngay` (`ngay_id`);

    alter table `khoan_chi` 
       add constraint `FK6uq8fsfwuhae2nelrpck5tder` 
       foreign key (`pttt_id`) 
       references `phuong_thuc_thanh_toan` (`pttt_id`);

    alter table `khoan_chi` 
       add constraint `FK4i1rpjeidadllsu83qnc1olt6` 
       foreign key (`vt_id`) 
       references `vi_tien` (`vt_id`);

    alter table `khoan_thu` 
       add constraint `FK7iw6xliyqcqr1651u1b3tbo1b` 
       foreign key (`lkt_id`) 
       references `loai_khoan_thu` (`lkt_id`);

    alter table `khoan_thu` 
       add constraint `FKmw58v68fh7vh57ak6wwtne0yo` 
       foreign key (`nd_id`) 
       references `nguoi_dung` (`nd_id`);

    alter table `khoan_thu` 
       add constraint `FKmcym9xyoejj20nie2mpbjmxu7` 
       foreign key (`ngay_id`) 
       references `ngay` (`ngay_id`);

    alter table `khoan_thu` 
       add constraint `FKt1cjgq2yv3dujbsw3kl4xjt49` 
       foreign key (`vt_id`) 
       references `vi_tien` (`vt_id`);

    alter table `nguoi_dung` 
       add constraint `FKqxftvm80i2pwm2rsjp9nrktbs` 
       foreign key (`pq_id`) 
       references `phan_quyen` (`pq_id`);

    alter table `quyen_truy_cap` 
       add constraint `FKdycooh5vvx5mqwt4dk62g5737` 
       foreign key (`cn_id`) 
       references `chuc_nang` (`cn_id`);

    alter table `quyen_truy_cap` 
       add constraint `FK158o5s4h1dol05fo8c6wbslh7` 
       foreign key (`pq_id`) 
       references `phan_quyen` (`pq_id`);

    alter table `so_hu` 
       add constraint `FK76re6ogdllmku4072wq26v56i` 
       foreign key (`nd_id`) 
       references `nguoi_dung` (`nd_id`);

    alter table `so_hu` 
       add constraint `FKepcd0f9kbvpyad4vow4kud5qv` 
       foreign key (`vt_id`) 
       references `vi_tien` (`vt_id`);

    create table `vi_tien` (
        `lv_id` integer not null,
        `tt_id` integer not null,
        `vt_id` integer not null auto_increment,
        `vt_so_du` decimal(10,2) not null,
        primary key (`vt_id`)
    ) engine=InnoDB;

    create table `chuc_nang` (
        `cn_id` integer not null auto_increment,
        `cn_ten` varchar(256) not null,
        primary key (`cn_id`)
    ) engine=InnoDB;

    create table `chuc_nang_quyen_truy_caps` (
        `chuc_nang_cn_id` integer not null,
        `quyen_truy_caps_cn_id` integer not null,
        primary key (`chuc_nang_cn_id`, `quyen_truy_caps_cn_id`)
    ) engine=InnoDB;

    create table `han_muc_chi` (
        `LKC_Id` integer not null,
        `Ngay_Id` integer not null,
        `VT_Id` integer not null,
        `hmc_so_tien` decimal(10,2) not null,
        `lkc_id` integer not null,
        `ngay_id` integer not null,
        `tt_id` integer not null,
        `vt_id` integer not null,
        `hmc_mo_ta` tinytext,
        primary key (`LKC_Id`, `Ngay_Id`, `VT_Id`)
    ) engine=InnoDB;

    create table `khoan_chi` (
        `kc_id` integer not null auto_increment,
        `kc_lap_lai` bit not null,
        `kc_ngay_ket_thuc_lap_lai` date not null,
        `kc_so_tien` decimal(10,2) not null,
        `lkc_id` integer not null,
        `nd_id` integer not null,
        `ngay_id` integer not null,
        `pttt_id` integer not null,
        `vt_id` integer not null,
        `kc_mo_ta` tinytext,
        primary key (`kc_id`)
    ) engine=InnoDB;

    create table `khoan_thu` (
        `kt_id` integer not null auto_increment,
        `kt_so_tien` decimal(10,2) not null,
        `lkt_id` integer not null,
        `nd_id` integer not null,
        `ngay_id` integer not null,
        `vt_id` integer not null,
        primary key (`kt_id`)
    ) engine=InnoDB;

    create table `loai_khoan_chi` (
        `lkc_id` integer not null auto_increment,
        `lkc_ten` varchar(255) not null,
        `lkc_mo_ta` tinytext,
        primary key (`lkc_id`)
    ) engine=InnoDB;

    create table `loai_khoan_thu` (
        `lkt_id` integer not null,
        `lkt_ten` varchar(256) not null,
        primary key (`lkt_id`)
    ) engine=InnoDB;

    create table `loai_vi` (
        `lv_id` integer not null auto_increment,
        `lv_ten` varchar(256) not null,
        primary key (`lv_id`)
    ) engine=InnoDB;

    create table `ngay` (
        `ngay_id` integer not null auto_increment,
        `ngay_trong_nam` integer not null,
        `ngay_trong_thang` integer not null,
        `ngay_trong_tuan` integer not null,
        `ngay_daydu` datetime(6),
        primary key (`ngay_id`)
    ) engine=InnoDB;

    create table `nguoi_dung` (
        `nd_id` integer not null auto_increment,
        `pq_id` integer,
        `nd_ngay_tao` datetime(6) default CURRENT_TIMESTAMP,
        `nd_avatar` varchar(10),
        `nd_trang_thai` varchar(50) not null,
        `nd_email` varchar(255),
        `nd_mat_khau` varchar(255) not null,
        `nd_ten` varchar(255) not null,
        primary key (`nd_id`)
    ) engine=InnoDB;

    create table `phan_quyen` (
        `pq_cap_do` integer not null,
        `pq_id` integer not null auto_increment,
        `pq_ten` varchar(256) not null,
        primary key (`pq_id`)
    ) engine=InnoDB;

    create table `phuong_thuc_thanh_toan` (
        `pttt_id` integer not null auto_increment,
        `pttt_loai` varchar(100) not null,
        `pttt_so_tk` tinytext,
        primary key (`pttt_id`)
    ) engine=InnoDB;

    create table `quyen_truy_cap` (
        `cn_id` integer not null,
        `pq_id` integer,
        `qtc_cho_phep` bit not null,
        primary key (`cn_id`)
    ) engine=InnoDB;

    create table `so_hu` (
        `nd_id` integer not null,
        `vt_id` integer not null,
        primary key (`nd_id`, `vt_id`)
    ) engine=InnoDB;

    create table `trang_thai` (
        `tt_id` integer not null auto_increment,
        `tt_ten` varchar(256) not null,
        `tt_mota` tinytext not null,
        primary key (`tt_id`)
    ) engine=InnoDB;

    alter table `chuc_nang_quyen_truy_caps` 
       add constraint `UK1m3xjacixiwmwo3j7u9d8dh5n` unique (`quyen_truy_caps_cn_id`);

    alter table `vi_tien` 
       add constraint `FKrmrwq1ism31a35g2qqtuqnbyk` 
       foreign key (`lv_id`) 
       references `loai_vi` (`lv_id`);

    alter table `vi_tien` 
       add constraint `FKhvj3f0a7ajd2kmvnvsi3tnelh` 
       foreign key (`tt_id`) 
       references `trang_thai` (`tt_id`);

    alter table `chuc_nang_quyen_truy_caps` 
       add constraint `FK6mc9yb29a37nippkgr8u5oeqf` 
       foreign key (`quyen_truy_caps_cn_id`) 
       references `quyen_truy_cap` (`cn_id`);

    alter table `chuc_nang_quyen_truy_caps` 
       add constraint `FK3v1582tmmfii6wilbhbb0wwov` 
       foreign key (`chuc_nang_cn_id`) 
       references `chuc_nang` (`cn_id`);

    alter table `han_muc_chi` 
       add constraint `FK7e5nxi1vs3mtxolwjk63naqax` 
       foreign key (`lkc_id`) 
       references `loai_khoan_chi` (`lkc_id`);

    alter table `han_muc_chi` 
       add constraint `FKo7492u5a6x547o5tfhfdnfqwq` 
       foreign key (`ngay_id`) 
       references `ngay` (`ngay_id`);

    alter table `han_muc_chi` 
       add constraint `FKkiravinknsh9kaoxc43ksutrd` 
       foreign key (`tt_id`) 
       references `trang_thai` (`tt_id`);

    alter table `han_muc_chi` 
       add constraint `FKeytel4slec66i0uqqrfpegg7h` 
       foreign key (`vt_id`) 
       references `vi_tien` (`vt_id`);

    alter table `khoan_chi` 
       add constraint `FKg4wewtsqgy0k2jo788w6bkjfn` 
       foreign key (`lkc_id`) 
       references `loai_khoan_chi` (`lkc_id`);

    alter table `khoan_chi` 
       add constraint `FKgthhfiy258xa7407tmnmyx90` 
       foreign key (`nd_id`) 
       references `nguoi_dung` (`nd_id`);

    alter table `khoan_chi` 
       add constraint `FKiw186g4r7q9ah3pmxx6pfmyqb` 
       foreign key (`ngay_id`) 
       references `ngay` (`ngay_id`);

    alter table `khoan_chi` 
       add constraint `FK6uq8fsfwuhae2nelrpck5tder` 
       foreign key (`pttt_id`) 
       references `phuong_thuc_thanh_toan` (`pttt_id`);

    alter table `khoan_chi` 
       add constraint `FK4i1rpjeidadllsu83qnc1olt6` 
       foreign key (`vt_id`) 
       references `vi_tien` (`vt_id`);

    alter table `khoan_thu` 
       add constraint `FK7iw6xliyqcqr1651u1b3tbo1b` 
       foreign key (`lkt_id`) 
       references `loai_khoan_thu` (`lkt_id`);

    alter table `khoan_thu` 
       add constraint `FKmw58v68fh7vh57ak6wwtne0yo` 
       foreign key (`nd_id`) 
       references `nguoi_dung` (`nd_id`);

    alter table `khoan_thu` 
       add constraint `FKmcym9xyoejj20nie2mpbjmxu7` 
       foreign key (`ngay_id`) 
       references `ngay` (`ngay_id`);

    alter table `khoan_thu` 
       add constraint `FKt1cjgq2yv3dujbsw3kl4xjt49` 
       foreign key (`vt_id`) 
       references `vi_tien` (`vt_id`);

    alter table `nguoi_dung` 
       add constraint `FKqxftvm80i2pwm2rsjp9nrktbs` 
       foreign key (`pq_id`) 
       references `phan_quyen` (`pq_id`);

    alter table `quyen_truy_cap` 
       add constraint `FKdycooh5vvx5mqwt4dk62g5737` 
       foreign key (`cn_id`) 
       references `chuc_nang` (`cn_id`);

    alter table `quyen_truy_cap` 
       add constraint `FK158o5s4h1dol05fo8c6wbslh7` 
       foreign key (`pq_id`) 
       references `phan_quyen` (`pq_id`);

    alter table `so_hu` 
       add constraint `FK76re6ogdllmku4072wq26v56i` 
       foreign key (`nd_id`) 
       references `nguoi_dung` (`nd_id`);

    alter table `so_hu` 
       add constraint `FKepcd0f9kbvpyad4vow4kud5qv` 
       foreign key (`vt_id`) 
       references `vi_tien` (`vt_id`);

    create table `vi_tien` (
        `lv_id` integer not null,
        `tt_id` integer not null,
        `vt_id` integer not null auto_increment,
        `vt_so_du` decimal(10,2) not null,
        primary key (`vt_id`)
    ) engine=InnoDB;

    create table `chuc_nang` (
        `cn_id` integer not null auto_increment,
        `cn_ten` varchar(256) not null,
        primary key (`cn_id`)
    ) engine=InnoDB;

    create table `chuc_nang_quyen_truy_caps` (
        `chuc_nang_cn_id` integer not null,
        `quyen_truy_caps_cn_id` integer not null,
        primary key (`chuc_nang_cn_id`, `quyen_truy_caps_cn_id`)
    ) engine=InnoDB;

    create table `han_muc_chi` (
        `LKC_Id` integer not null,
        `Ngay_Id` integer not null,
        `VT_Id` integer not null,
        `hmc_so_tien` decimal(10,2) not null,
        `lkc_id` integer not null,
        `ngay_id` integer not null,
        `tt_id` integer not null,
        `vt_id` integer not null,
        `hmc_mo_ta` tinytext,
        primary key (`LKC_Id`, `Ngay_Id`, `VT_Id`)
    ) engine=InnoDB;

    create table `khoan_chi` (
        `kc_id` integer not null auto_increment,
        `kc_lap_lai` bit not null,
        `kc_ngay_ket_thuc_lap_lai` date not null,
        `kc_so_tien` decimal(10,2) not null,
        `lkc_id` integer not null,
        `nd_id` integer not null,
        `ngay_id` integer not null,
        `pttt_id` integer not null,
        `vt_id` integer not null,
        `kc_mo_ta` tinytext,
        primary key (`kc_id`)
    ) engine=InnoDB;

    create table `khoan_thu` (
        `kt_id` integer not null auto_increment,
        `kt_so_tien` decimal(10,2) not null,
        `lkt_id` integer not null,
        `nd_id` integer not null,
        `ngay_id` integer not null,
        `vt_id` integer not null,
        primary key (`kt_id`)
    ) engine=InnoDB;

    create table `loai_khoan_chi` (
        `lkc_id` integer not null auto_increment,
        `lkc_ten` varchar(255) not null,
        `lkc_mo_ta` tinytext,
        primary key (`lkc_id`)
    ) engine=InnoDB;

    create table `loai_khoan_thu` (
        `lkt_id` integer not null,
        `lkt_ten` varchar(256) not null,
        primary key (`lkt_id`)
    ) engine=InnoDB;

    create table `loai_vi` (
        `lv_id` integer not null auto_increment,
        `lv_ten` varchar(256) not null,
        primary key (`lv_id`)
    ) engine=InnoDB;

    create table `ngay` (
        `ngay_id` integer not null auto_increment,
        `ngay_trong_nam` integer not null,
        `ngay_trong_thang` integer not null,
        `ngay_trong_tuan` integer not null,
        `ngay_daydu` datetime(6),
        primary key (`ngay_id`)
    ) engine=InnoDB;

    create table `nguoi_dung` (
        `nd_id` integer not null auto_increment,
        `pq_id` integer,
        `nd_ngay_tao` datetime(6) default CURRENT_TIMESTAMP,
        `nd_avatar` varchar(10),
        `nd_trang_thai` varchar(50) not null,
        `nd_email` varchar(255),
        `nd_mat_khau` varchar(255) not null,
        `nd_ten` varchar(255) not null,
        primary key (`nd_id`)
    ) engine=InnoDB;

    create table `phan_quyen` (
        `pq_cap_do` integer not null,
        `pq_id` integer not null auto_increment,
        `pq_ten` varchar(256) not null,
        primary key (`pq_id`)
    ) engine=InnoDB;

    create table `phuong_thuc_thanh_toan` (
        `pttt_id` integer not null auto_increment,
        `pttt_loai` varchar(100) not null,
        `pttt_so_tk` tinytext,
        primary key (`pttt_id`)
    ) engine=InnoDB;

    create table `quyen_truy_cap` (
        `cn_id` integer not null,
        `pq_id` integer,
        `qtc_cho_phep` bit not null,
        primary key (`cn_id`)
    ) engine=InnoDB;

    create table `so_hu` (
        `nd_id` integer not null,
        `vt_id` integer not null,
        primary key (`nd_id`, `vt_id`)
    ) engine=InnoDB;

    create table `trang_thai` (
        `tt_id` integer not null auto_increment,
        `tt_ten` varchar(256) not null,
        `tt_mota` tinytext not null,
        primary key (`tt_id`)
    ) engine=InnoDB;

    alter table `chuc_nang_quyen_truy_caps` 
       add constraint `UK1m3xjacixiwmwo3j7u9d8dh5n` unique (`quyen_truy_caps_cn_id`);

    alter table `vi_tien` 
       add constraint `FKrmrwq1ism31a35g2qqtuqnbyk` 
       foreign key (`lv_id`) 
       references `loai_vi` (`lv_id`);

    alter table `vi_tien` 
       add constraint `FKhvj3f0a7ajd2kmvnvsi3tnelh` 
       foreign key (`tt_id`) 
       references `trang_thai` (`tt_id`);

    alter table `chuc_nang_quyen_truy_caps` 
       add constraint `FK6mc9yb29a37nippkgr8u5oeqf` 
       foreign key (`quyen_truy_caps_cn_id`) 
       references `quyen_truy_cap` (`cn_id`);

    alter table `chuc_nang_quyen_truy_caps` 
       add constraint `FK3v1582tmmfii6wilbhbb0wwov` 
       foreign key (`chuc_nang_cn_id`) 
       references `chuc_nang` (`cn_id`);

    alter table `han_muc_chi` 
       add constraint `FK7e5nxi1vs3mtxolwjk63naqax` 
       foreign key (`lkc_id`) 
       references `loai_khoan_chi` (`lkc_id`);

    alter table `han_muc_chi` 
       add constraint `FKo7492u5a6x547o5tfhfdnfqwq` 
       foreign key (`ngay_id`) 
       references `ngay` (`ngay_id`);

    alter table `han_muc_chi` 
       add constraint `FKkiravinknsh9kaoxc43ksutrd` 
       foreign key (`tt_id`) 
       references `trang_thai` (`tt_id`);

    alter table `han_muc_chi` 
       add constraint `FKeytel4slec66i0uqqrfpegg7h` 
       foreign key (`vt_id`) 
       references `vi_tien` (`vt_id`);

    alter table `khoan_chi` 
       add constraint `FKg4wewtsqgy0k2jo788w6bkjfn` 
       foreign key (`lkc_id`) 
       references `loai_khoan_chi` (`lkc_id`);

    alter table `khoan_chi` 
       add constraint `FKgthhfiy258xa7407tmnmyx90` 
       foreign key (`nd_id`) 
       references `nguoi_dung` (`nd_id`);

    alter table `khoan_chi` 
       add constraint `FKiw186g4r7q9ah3pmxx6pfmyqb` 
       foreign key (`ngay_id`) 
       references `ngay` (`ngay_id`);

    alter table `khoan_chi` 
       add constraint `FK6uq8fsfwuhae2nelrpck5tder` 
       foreign key (`pttt_id`) 
       references `phuong_thuc_thanh_toan` (`pttt_id`);

    alter table `khoan_chi` 
       add constraint `FK4i1rpjeidadllsu83qnc1olt6` 
       foreign key (`vt_id`) 
       references `vi_tien` (`vt_id`);

    alter table `khoan_thu` 
       add constraint `FK7iw6xliyqcqr1651u1b3tbo1b` 
       foreign key (`lkt_id`) 
       references `loai_khoan_thu` (`lkt_id`);

    alter table `khoan_thu` 
       add constraint `FKmw58v68fh7vh57ak6wwtne0yo` 
       foreign key (`nd_id`) 
       references `nguoi_dung` (`nd_id`);

    alter table `khoan_thu` 
       add constraint `FKmcym9xyoejj20nie2mpbjmxu7` 
       foreign key (`ngay_id`) 
       references `ngay` (`ngay_id`);

    alter table `khoan_thu` 
       add constraint `FKt1cjgq2yv3dujbsw3kl4xjt49` 
       foreign key (`vt_id`) 
       references `vi_tien` (`vt_id`);

    alter table `nguoi_dung` 
       add constraint `FKqxftvm80i2pwm2rsjp9nrktbs` 
       foreign key (`pq_id`) 
       references `phan_quyen` (`pq_id`);

    alter table `quyen_truy_cap` 
       add constraint `FKdycooh5vvx5mqwt4dk62g5737` 
       foreign key (`cn_id`) 
       references `chuc_nang` (`cn_id`);

    alter table `quyen_truy_cap` 
       add constraint `FK158o5s4h1dol05fo8c6wbslh7` 
       foreign key (`pq_id`) 
       references `phan_quyen` (`pq_id`);

    alter table `so_hu` 
       add constraint `FK76re6ogdllmku4072wq26v56i` 
       foreign key (`nd_id`) 
       references `nguoi_dung` (`nd_id`);

    alter table `so_hu` 
       add constraint `FKepcd0f9kbvpyad4vow4kud5qv` 
       foreign key (`vt_id`) 
       references `vi_tien` (`vt_id`);
