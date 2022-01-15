CREATE TABLE contractdata (
    "label" CHAR(50),
    "candidateId" INT,
    "name" CHAR(50),
    "marketId" INT,
    "marketName" CHAR(50)
);

CREATE TABLE contrac_log(
    "id" BIGINT,
    "candidateId" INT,
    "lastTradePrice" DOUBLE PRECISION,
    "timeStamp" TIMESTAMP,
    "bestBuyYesCost" DOUBLE PRECISION,
    "bestBuyNoCost" DOUBLE PRECISION
);

INSERT INTO contractdata VALUES
        ("liberal",28839,"Draghi",7663,"president-italia"),
        ("conservative",28836,"Berlusconi",7663,"president-italia"),
        ("liberal",26820,"Macron",7360,"president-france"),
        ("conservative",28824,"Pécresse",7360,"president-france"),
        ("conservative",28816,"Le Pen",7360,"president-france"),
        ("conservative",28712,"Orbán",7643,"president-hungary"),
        ("liberal",28713,"Márki-Zay",7643,"president-hungary"),
        ("conservative",28846,"Johnson",7665,"johnson-stays"),
        ("liberal",28666,"Johnson",7636,"europe-out"),
        ("conservative",28659,"Draghi",7636,"europe-out"),
        ("conservative",28668,"Macron",7636,"europe-out"),
        ("liberal",28665,"Orbán",7636,"europe-out");

