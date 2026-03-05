-- ledger_category
INSERT INTO ledger_category (name, color, sort_order) VALUES
  ('식비', '#262626', 1),
  ('교통', '#262626', 2),
  ('주거/통신', '#262626', 3),
  ('쇼핑', '#262626', 4),
  ('급여', '#262626', 5);

-- payment_method
INSERT INTO payment_method (name, color, method_type) VALUES
  ('현금', '#262626', 'C'),
  ('체크카드', '#262626', 'D'),
  ('신용카드', '#262626', 'R');

-- ledger_entry
INSERT INTO ledger_entry (entry_date, entry_type, amount, title, memo, category_id) VALUES
  ('2026-02-20', 'E', 12000, '점심', '회사 구내식당', 1),
  ('2026-02-21', 'E', 4500, '버스', '출퇴근 교통비', 2),
  ('2026-02-22', 'E', 30000, '장보기', '주말 마트', 1),
  ('2026-02-23', 'E', 8000, '통신요금', '월 요금', 3),
  ('2026-02-24', 'I', 2500000, '급여', '2월 급여', 5),
  ('2026-01-10', 'E', 15000, '점심', '1월 점심', 1),
  ('2026-01-25', 'I', 200000, '부수입', '1월 부수입', 5);
