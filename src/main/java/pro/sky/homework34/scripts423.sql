SELECT students.name, students.age, faculties.name
FROM students
FULL JOIN faculty ON students.faculty_id = faculty.id
SELECT students.name
FROM Avatar
INNER JOIN students ON avatar.student_id = students.id