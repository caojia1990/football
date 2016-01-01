/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2015/12/26 11:44:46                          */
/*==============================================================*/


drop table if exists T_EVENT_INFO;

drop table if exists T_MATCH;

drop table if exists T_ODDS;

drop table if exists T_TEAM;

drop table if exists T_TEAM_CUP_SEASON;

drop table if exists T_TEAM_LUAGUE_SEASON;

/*==============================================================*/
/* Table: T_EVENT_INFO                                          */
/*==============================================================*/
create table T_EVENT_INFO
(
   ID                   bigint not null,
   EVENT_NAME           varchar(255),
   EVENT_SHORT_NAME     varchar(50),
   EVENT_TYPE           char comment '0：联赛
            1：杯赛',
   CONTINENT            varchar(10),
   COUNTRY              varchar(50),
   primary key (ID)
);

/*==============================================================*/
/* Table: T_MATCH                                               */
/*==============================================================*/
create table T_MATCH
(
   ID                   bigint not null,
   MATCH_NO             varchar(32) comment '比赛编号',
   HOST_TEAM_NO         varchar(32) comment '主队球队编号',
   HOST_SHORT_NAME      varchar(50) comment '主队简称',
   GUEST_TEAM_NO        varchar(32) comment '客队球队编号',
   GUEST_SHORT_NAME     varchar(50) comment '客队简称',
   EVENT_ID             bigint,
   SEASON_NAME          varchar(50),
   MATCH_TIME           datetime comment '比赛时间',
   ROUND                int comment '轮次',
   HOST_GOAL            int comment '主队进球',
   GUEST_GOAL           int comment '客队进球',
   HALF_TIME_HOST_GOAL  int comment '半场主队进球',
   HALF_TIME_GUEST_GOAL int comment '半场客队进球',
   MATCH_STATUS         char comment '0：未开始
            1：比赛中
            2：已结束',
   primary key (ID)
);

/*==============================================================*/
/* Table: T_ODDS                                                */
/*==============================================================*/
create table T_ODDS
(
   ID                   bigint not null,
   MATCH_NO             varchar(32) not null,
   COMPANY              varchar(50),
   CHANGE_TIME          datetime,
   WIN                  decimal(5,2),
   DRAW                 decimal(5,2),
   LOSE                 decimal(5,2),
   FIRST_ODDS           char comment '0：否
            1：是',
   primary key (ID)
);

/*==============================================================*/
/* Table: T_TEAM                                                */
/*==============================================================*/
create table T_TEAM
(
   ID                   bigint not null,
   TEAM_NO              varchar(32) comment '球队编号',
   TEAM_NAME            varchar(255) comment '球队名称',
   SHORT_NAME           varchar(50) comment '球队简称',
   TEAM_NAME_ENG        varchar(255) comment '球队英文名',
   TEAM_TYPE            char not null comment '0：国家队；1：俱乐部',
   CONTINENT            varchar(10) comment '0：亚洲（Asia）
            1：欧洲（Europe）
            2：非洲（Africa）
            3：美洲（America）
            4：大洋洲（Oceania）',
   COUNTRY              varchar(50) comment '所属国家',
   ESTABLISH_DATE       date comment '成立日期',
   primary key (ID)
);

/*==============================================================*/
/* Table: T_TEAM_CUP_SEASON                                     */
/*==============================================================*/
create table T_TEAM_CUP_SEASON
(
   ID                   bigint not null,
   TEAM_NO              bigint,
   TEAM_SHORT_NAME      varchar(50),
   CUP_ID               bigint,
   SEASON_NAME          varchar(50),
   HAVE_MATCH           int,
   WIN                  int,
   DRAW                 int,
   LOSE                 int,
   GOALS                int,
   FUMBLE               int,
   HOST_HAVE_MATCH      int,
   HOST_WIN             int,
   HOST_DRAW            int,
   HOST_LOSE            int,
   HOST_GOALS           int,
   HOST_FUMBLE          int,
   GUEST__HAVE_MATCH    int,
   GUEST_WIN            int,
   GUEST_DRAW           int,
   GUEST_LOSE           int,
   GUEST_GOALS          int,
   GUEST_FUMBLE         int,
   POINTS               int,
   GROUP_NAME           char,
   GROUP_RANKING        int,
   IS_RISE              char comment '0：否
            1：是',
   primary key (ID)
);

/*==============================================================*/
/* Table: T_TEAM_LUAGUE_SEASON                                  */
/*==============================================================*/
create table T_TEAM_LUAGUE_SEASON
(
   ID                   bigint not null,
   TEAM_NO              bigint,
   TEAM_SHORT_NAME      varchar(50),
   LEAGUE_ID            bigint,
   SEASON_NAME          varchar(50),
   HAVE_MATCH           int,
   WIN                  int,
   DRAW                 int,
   LOSE                 int,
   GOALS                int,
   FUMBLE               int,
   HOST_HAVE_MATCH      int,
   HOST_WIN             int,
   HOST_DRAW            int,
   HOST_LOSE            int,
   HOST_GOALS           int,
   HOST_FUMBLE          int,
   GUEST_HAVE_MATCH     int,
   GUEST_WIN            int,
   GUEST_DRAW           int,
   GUEST_LOSE           int,
   GUEST_GOALS          int,
   GUEST_FUMBLE         int,
   POINTS               int,
   primary key (ID)
);

