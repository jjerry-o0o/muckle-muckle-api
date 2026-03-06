CREATE TABLE IF NOT EXISTS ledger_category (
    category_id             BIGINT          AUTO_INCREMENT PRIMARY KEY,
    name                    VARCHAR(50)     NOT NULL,
    color                   VARCHAR(7)      NOT NULL, -- 예 : #RRGGBB
    sort_order              INT             NOT NULL DEFAULT 0,
    created_at              TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at              TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS payment_method (
    payment_method_id       BIGINT          AUTO_INCREMENT PRIMARY KEY,
    name                    VARCHAR(50)     NOT NULL, -- 예: 현금, 신한카드, KB카드
    color                   VARCHAR(7)      NOT NULL, -- 예 : #RRGGBB
    method_type             VARCHAR(1)      NOT NULL, -- C.현금 / A.계좌이체 / D.체크카드 / R.신용카드 / E.기타>상품권,기프티콘 등
    created_at              TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at              TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT ck_method_type CHECK (method_type IN ('C', 'A', 'D', 'R', 'E'))
);

CREATE TABLE IF NOT EXISTS ledger_entry (
    entry_id                BIGINT          AUTO_INCREMENT PRIMARY KEY,
    entry_date              DATE            NOT NULL,
    entry_type              VARCHAR(1)      NOT NULL, -- 'E' (expense) / 'I' (income)
    amount                  INT             NOT NULL,
    title                   VARCHAR(100)    NOT NULL,
    memo                    VARCHAR(500),
    category_id             BIGINT          NOT NULL,
    payment_method_id       BIGINT          NOT NULL,
    created_at              TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at              TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES ledger_category (category_id),
    CONSTRAINT fk_payment FOREIGN KEY (payment_method_id) REFERENCES payment_method (payment_method_id),
    CONSTRAINT ck_entry_type CHECK (entry_type IN ('E', 'I'))
);