DROP TABLE IF EXISTS contractdata;
CREATE TABLE contractdata (
    "label" CHAR(50),
    "candidateid" INT,
    "name" CHAR(50),
    "marketid" INT,
    "marketname" CHAR(50)
);
DROP TABLE IF EXISTS contract_log;
CREATE TABLE contract_log(
    "id" BIGINT,
    "candidate_id" INT,
    "last_trade_price" DOUBLE PRECISION,
    "time_stamp" TIMESTAMP,
    "bestBuyYesCost" DOUBLE PRECISION,
    "bestBuyNoCost" DOUBLE PRECISION
);

INSERT INTO contractdata ("label",candidateid,"name",marketid,marketname) VALUES
        ('liberal',28839,'Draghi',7663,'president-italia'),
        ('conservative',28836,'Berlusconi',7663,'president-italia'),
        ('liberal',26820,'Macron',7360,'president-france'),
        ('conservative',28824,'Pécresse',7360,'president-france'),
        ('conservative',28816,'Le Pen',7360,'president-france'),
        ('conservative',28712,'Orbán',7643,'president-hungary'),
        ('liberal',28713,'Márki-Zay',7643,'president-hungary'),
        ('conservative',28846,'Johnson',7665,'johnson-stays'),
        ('liberal',28666,'Johnson',7636,'europe-out'),
        ('conservative',28659,'Draghi',7636,'europe-out'),
        ('conservative',28668,'Macron',7636,'europe-out'),
        ('liberal',28665,'Orbán',7636,'europe-out');

INSERT INTO contract_log ("candidate_id","last_trade_price","time_stamp") VALUES
    (28836,0.1,TO_TIMESTAMP('2021-12-02 18:52:30', 'YYYY-MM-DD HH24:MI:SS')),
    (28824,0.2,TO_TIMESTAMP('2021-12-02 18:57:10', 'YYYY-MM-DD HH24:MI:SS')),
    (28816,0.4,TO_TIMESTAMP('2021-12-02 18:48:05', 'YYYY-MM-DD HH24:MI:SS')),
    (28712,0.1,TO_TIMESTAMP('2021-12-02 18:41:13', 'YYYY-MM-DD HH24:MI:SS')),
    (28846,0.8,TO_TIMESTAMP('2021-12-02 19:01:12', 'YYYY-MM-DD HH24:MI:SS')),
    (28659,0.7,TO_TIMESTAMP('2021-12-02 18:42:16', 'YYYY-MM-DD HH24:MI:SS')),
    (28668,0.6,TO_TIMESTAMP('2021-12-01 14:50:11', 'YYYY-MM-DD HH24:MI:SS')),
    (28836,0.9,TO_TIMESTAMP('2021-12-01 12:51:00', 'YYYY-MM-DD HH24:MI:SS')),
    (28668,0.2,TO_TIMESTAMP('2021-11-30 12:50:25', 'YYYY-MM-DD HH24:MI:SS')),
    (28668,0.2,TO_TIMESTAMP('2021-11-20 18:50:25', 'YYYY-MM-DD HH24:MI:SS')),

    (28665,0.1,TO_TIMESTAMP('2021-12-03 20:51:12', 'YYYY-MM-DD HH24:MI:SS')),
    (28666,0.3,TO_TIMESTAMP('2021-12-03 20:50:05', 'YYYY-MM-DD HH24:MI:SS')),
    (28713,0.4,TO_TIMESTAMP('2021-12-03 20:43:17', 'YYYY-MM-DD HH24:MI:SS')),
    (26820,0.2,TO_TIMESTAMP('2021-12-03 20:39:12', 'YYYY-MM-DD HH24:MI:SS')),
    (28839,0.6,TO_TIMESTAMP('2021-12-03 21:01:56', 'YYYY-MM-DD HH24:MI:SS')),
    (28665,0.8,TO_TIMESTAMP('2021-12-03 20:57:46', 'YYYY-MM-DD HH24:MI:SS')),
    (28713,0.3,TO_TIMESTAMP('2021-12-02 11:57:32', 'YYYY-MM-DD HH24:MI:SS')),
    (28665,0.1,TO_TIMESTAMP('2021-11-29 22:13:46', 'YYYY-MM-DD HH24:MI:SS')),
    (28713,0.2,TO_TIMESTAMP('2021-11-29 18:51:12', 'YYYY-MM-DD HH24:MI:SS')),
    (28713,0.2,TO_TIMESTAMP('2021-11-20 18:51:12', 'YYYY-MM-DD HH24:MI:SS'));