CREATE TABLE queue_reservation
(
    id_queue_reservation BIGSERIAL PRIMARY KEY,
    date DATE,
    time_begin TIME,
    time_end TIME,
    admin_permission BOOLEAN,
    id_employee BIGSERIAL references employee(id_employee),
    id_work_place BIGSERIAL references work_place(id_work_place)
)