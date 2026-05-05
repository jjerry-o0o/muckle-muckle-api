-- 사용자 정보
CREATE TABLE IF NOT EXISTS users (
    user_id                 BIGINT          PRIMARY KEY AUTO_INCREMENT,
    user_email              VARCHAR(50)     NOT NULL UNIQUE,
    user_password           VARCHAR(255)    NOT NULL,
    user_nickname           VARCHAR(20)     NOT NULL UNIQUE,
    created_at              TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at              TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 장부 카테고리
CREATE TABLE IF NOT EXISTS ledger_category (
    ledger_category_id      BIGINT          PRIMARY KEY AUTO_INCREMENT,
    user_id                 BIGINT          NOT NULL,
    name                    VARCHAR(10)     NOT NULL,
    color                   VARCHAR(7)      NOT NULL, -- 예 : #RRGGBB
    sort_order              INT             NOT NULL DEFAULT 0,
    created_at              TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at              TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_ledger_category_users FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT uk_ledger_category UNIQUE (user_id, name)
);

-- 결제 방법
CREATE TABLE IF NOT EXISTS payment_method (
    payment_method_id       BIGINT          PRIMARY KEY AUTO_INCREMENT,
    user_id                 BIGINT          NOT NULL,
    name                    VARCHAR(10)     NOT NULL, -- 예: 현금, 신한카드, KB카드
    color                   VARCHAR(7)      NOT NULL, -- 예 : #RRGGBB
    method_type             VARCHAR(1)      NOT NULL, -- C.현금 / A.계좌이체 / D.체크카드 / R.신용카드 / E.기타>상품권,기프티콘 등
    created_at              TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at              TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_payment_method_users FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT uk_payment_method UNIQUE (user_id, name),
    CONSTRAINT ck_payment_method CHECK (method_type IN ('C', 'A', 'D', 'R', 'E'))
);

-- 장부 내역
CREATE TABLE IF NOT EXISTS ledger_entry (
    entry_id                BIGINT          PRIMARY KEY AUTO_INCREMENT,
    user_id                 BIGINT          NOT NULL,
    ledger_category_id      BIGINT          NOT NULL,
    payment_method_id       BIGINT          NOT NULL,
    entry_date              DATE            NOT NULL,
    entry_type              VARCHAR(1)      NOT NULL, -- 'E' (expense) / 'I' (income)
    amount                  DECIMAL(18,2)   NOT NULL,
    title                   VARCHAR(20)     NOT NULL,
    memo                    VARCHAR(30),
    created_at              TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at              TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_ledger_entry_users FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT fk_ledger_entry_ledger_category FOREIGN KEY (ledger_category_id) REFERENCES ledger_category (ledger_category_id),
    CONSTRAINT fk_ledger_entry_payment_method FOREIGN KEY (payment_method_id) REFERENCES payment_method (payment_method_id),
    CONSTRAINT ck_entry_type CHECK (entry_type IN ('E', 'I'))
);

-- 자산 카테고리
CREATE TABLE IF NOT EXISTS asset_category (
    asset_category_id       BIGINT          PRIMARY KEY AUTO_INCREMENT,
    user_id                 BIGINT          NOT NULL,
    name                    VARCHAR(10)     NOT NULL,
    sort_order              INT             NOT NULL DEFAULT 0,
    is_active               VARCHAR(1)      NOT NULL DEFAULT 'Y',
    created_at              TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at              TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_asset_category_users FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT uk_asset_category UNIQUE (user_id, name),
    CONSTRAINT ck_asset_category_is_active CHECK (is_active IN ('Y', 'N'))
);

-- 자산 항목
CREATE TABLE IF NOT EXISTS asset (
    asset_id                BIGINT          PRIMARY KEY AUTO_INCREMENT,
    user_id                 BIGINT          NOT NULL,
    asset_category_id       BIGINT          NOT NULL,
    name                    VARCHAR(30)     NOT NULL,
    institution_name        VARCHAR(30),
    is_debt                 VARCHAR(1)      NOT NULL DEFAULT 'N',
    memo                    VARCHAR(30),
    sort_order              INT             NOT NULL DEFAULT 0,
    is_active               VARCHAR(1)      NOT NULL DEFAULT 'Y',
    created_at              TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at              TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_asset_users FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT fk_asset_asset_category FOREIGN KEY (asset_category_id) REFERENCES asset_category (asset_category_id),
    CONSTRAINT uk_asset UNIQUE (user_id, name),
    CONSTRAINT ck_asset_is_debt CHECK (is_debt IN ('Y', 'N')),
    CONSTRAINT ck_asset_is_active CHECK (is_active IN ('Y', 'N'))
);

-- 자산 항목별 실제 금액
CREATE TABLE IF NOT EXISTS asset_current_value (
    asset_id                BIGINT          PRIMARY KEY,
    quantity                DECIMAL(18,6),
    unit_price              DECIMAL(18,2),
    current_value           DECIMAL(18,2)   NOT NULL DEFAULT 0,
    currency_code           VARCHAR(5)      NOT NULL,
    exchange_rate           DECIMAL(10,2),
    created_at              TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at              TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_asset_current_value_asset FOREIGN KEY (asset_id) REFERENCES asset (asset_id)
);

-- 자산 스냅샷
CREATE TABLE IF NOT EXISTS asset_daily_snapshot (
    snapshot_id             BIGINT          PRIMARY KEY AUTO_INCREMENT,
    asset_id                BIGINT          NOT NULL,
    snapshot_date           DATE            NOT NULL,
    quantity                DECIMAL(18,6),
    unit_price              DECIMAL(18,2),
    current_value           DECIMAL(18,2)   NOT NULL DEFAULT 0,
    currency_code           VARCHAR(5)      NOT NULL,
    exchange_rate           DECIMAL(10,2),
    created_at              TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at              TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_asset_daily_snapshot_asset FOREIGN KEY (asset_id) REFERENCES asset (asset_id),
    CONSTRAINT uk_asset_daily_snapshot UNIQUE (asset_id, snapshot_date)
);