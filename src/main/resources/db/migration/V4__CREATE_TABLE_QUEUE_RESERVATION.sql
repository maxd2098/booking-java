CREATE TABLE queue_reservation
(
    id_queue_reservation SERIAL PRIMARY KEY,
    date DATE,
    time_begin TIME,
    time_end TIME,
    admin_permission BOOLEAN,
    id_employee BIGINT references employee(id_employee),
    id_employee_id_employee BIGINT
)