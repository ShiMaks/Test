
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class StudentGroup implements GroupOperationService {

	private Student[] students;
	private int indexArray = 0;

	public StudentGroup(int length) {
		this.students = new Student[length];
	}

	@Override
	public Student[] getStudents() {
		return this.students;
	}

	@Override
	public void setStudents(Student[] students) {
		if (students == null) {
			throw new IllegalArgumentException();
		} else {
			this.students = students;
		}
	}

	@Override
	public Student getStudent(int index) {
		if (index < 0 || index >= students.length) {
			throw new IllegalArgumentException();
		} else
			return this.students[index];
	}

	@Override
	public void setStudent(Student student, int index) {
		if (student == null || index < 0 || index >= students.length) {
			throw new IllegalArgumentException();
		} else {
			if(this.students[index] != null) {
				this.students[index] = student;
			}else {
				this.students[index] = student;
				indexArray++;
			}
		}
	}

	@Override
	public void addFirst(Student student) {
		if (student == null) {
			throw new IllegalArgumentException();
		} else if (students[0] != null) {
			Student[] temp = students;
			students = new Student[temp.length + 1];
			System.arraycopy(temp, 0, students, 1, temp.length);
			students[0] = student;
			indexArray++;
		} else {
			students[0] = student;
			indexArray++;
		}
	}

	@Override
	public void addLast(Student student) {
		if (student == null) {
			throw new IllegalArgumentException();
		} else if (students[students.length - 1] != null) {
			Student[] temp = students;
			students = new Student[temp.length + 1];
			System.arraycopy(temp, 0, students, 0, temp.length);
			students[students.length - 1] = student;
			indexArray++;
		} else {
			students[students.length - 1] = student;
			indexArray++;
		}
	}

	@Override
	public void add(Student student, int index) {
		if (student == null || index < 0 || index >= students.length) {
			throw new IllegalArgumentException();
		} else if (indexArray == students.length - 1 || students[index] != null) {
			Student[] temp = students;
			students = new Student[temp.length + 1];
			int variableArray = temp.length - index;
			System.arraycopy(temp, 0, students, 0, index);
			System.arraycopy(temp, index, students, index + 1, variableArray);

		}
		students[index] = student;
		indexArray++;
	}

	@Override
	public void remove(int index) {
		if (students[index] == null || index >= students.length && index < 0) {
			throw new IllegalArgumentException();
		} else {
			Student[] temp = students;
			students = new Student[temp.length - 1];
			int variableArray = temp.length - index - 1;
			System.arraycopy(temp, 0, students, 0, index);
			System.arraycopy(temp, index + 1, students, index, variableArray);
			indexArray--;
		}
	}

	@Override
	public void remove(Student student) {
		if (student == null) {
			throw new IllegalArgumentException();
		} else {
			int iter = 0;
			for (int i = 0; i < students.length; i++) {
				if (students[i] != null && students[i] == student) {
					iter++;
					break;
				}
			}
			Student[] stud = new Student[students.length - 1];
			for(int i = 0; i < iter; i++) {
				stud[i] = students[i];
			}
			
			for(int i = iter + 1; i < students.length; i++) {
				stud[i -1] = students[i];
			}
			students = stud;
		}
	}

	@Override
	public void removeFromIndex(int index) {
		if (index < 0 || index >= students.length) {
			throw new IllegalArgumentException();
		} else if (index == 0) {
			Student[] temp = new Student[0];
			students = temp;
		} else {
			Student[] temp = new Student[index];
			for (int i = 0; i < temp.length; i++) {
				temp[i] = students[i];
			}
			students = temp;
		}
	}

	@Override
	public void removeFromElement(Student student) {
		int indexRemove = 0;
		if (student == null) {
			throw new IllegalArgumentException();
		} else {
			for (int i = 0; i < students.length; i++) {
				if (students[i] != null && students[i] == student) {
					indexRemove = i;
				}
			}
			this.removeFromIndex(indexRemove);
		}
	}

	@Override
	public void removeToIndex(int index) {
		if (index < 0 || index >= students.length) {
			throw new IllegalArgumentException();
		} else {
			Student[] temp = new Student[students.length - index];
			for (int i = 0; i < temp.length; i++) {
				temp[i] = students[i + index];
			}
			students = temp;
		}
	}

	@Override
	public void removeToElement(Student student) {
		int index = 0;
		if (student == null) {
			throw new IllegalArgumentException();
		} else {
			for (int i = 0; i < students.length; i++) {
				if (students[i] != null && students[i] == student) {
					index = i;
				}
			}
			this.removeToIndex(index);
		}
	}

	@Override
	public void bubbleSort() {
		int studentIter = 0;
		Student[] temp = students;
		for (int i = 0; i < temp.length; i++) {
			if (temp[i] != null) {
				students[studentIter] = temp[i];
				studentIter++;
			}
		}
		for (int i = 0; i < students.length; i++) {
			for (int j = i + 1; j < students.length; j++) {
				if (students[i] != null && students[j] != null
						&& students[i].getFullName().compareTo(students[j].getFullName()) > 0) {
					Student tempAr = students[j];
					students[j] = students[i];
					students[i] = tempAr;
				}
			}
		}
	}

	@Override
	public Student[] getByBirthDate(Date date) {

		int count = 0;
		if (date == null) {
			throw new IllegalArgumentException();
		} else {
			for (int i = 0; i < students.length; i++) {
				if (students[i] != null && students[i].getBirthDate().equals(date) == true) {
					count++;
				}
			}
		}
		if (count != 0) {
			Student[] student = new Student[count];
			int iter = 0;
			for (int i = 0; i < students.length; i++) {
				if (students[i] != null && students[i].getBirthDate().equals(date) == true) {
					student[iter++] = students[i];
				}
			}
			return student;
		} else {
			Student[] student = new Student[0];
			return student;
		}
	}

	@Override
	public Student[] getBetweenBirthDates(Date firstDate, Date lastDate) {
		int count = 0;
		if (firstDate == null || lastDate == null) {
			throw new IllegalArgumentException();
		} else {
			for (int i = 0; i < students.length; i++) {
				if (students[i] != null && students[i].getBirthDate().after(firstDate) == true
						&& students[i].getBirthDate().before(lastDate) == true) {
					count++;
				}
			}
		}
		if (count != 0) {
			Student[] student = new Student[count];
			int iter = 0;
			for (int i = 0; i < students.length; i++) {
				if (students[i] != null && students[i].getBirthDate().after(firstDate) == true
						&& students[i].getBirthDate().before(lastDate) == true) {
					student[iter++] = students[i];
				}
			}
			return student;
		} else {
			Student[] student = new Student[0];
			return student;
		}
	}

	@Override
	public Student[] getNearBirthDate(Date date, int days) {
		if (date == null) {
			throw new IllegalArgumentException();
		} else {
			int count = 0;
			Calendar dayTo = Calendar.getInstance();
			dayTo.setTime(date);
			dayTo.add(Calendar.DATE, days);
			Calendar dayBefore = Calendar.getInstance();
			dayBefore.setTime(date);
			dayBefore.add(Calendar.DATE, -days);
			for (int i = 0; i < students.length; i++) {
				if (students[i] != null && students[i].getBirthDate().after(dayBefore.getTime()) == true
						&& students[i].getBirthDate().before(dayTo.getTime()) == true) {
					count++;
				}
			}
			if (count != 0) {
				int iter = 0;
				Student[] student = new Student[count];
				for (int i = 0; i < students.length; i++) {
					if (students[i] != null && students[i].getBirthDate().after(dayBefore.getTime()) == true
							&& students[i].getBirthDate().before(dayTo.getTime()) == true) {
						student[iter++] = students[i];
					}
				}
				return student;
			} else {
				Student[] student = new Student[0];
				return student;
			}
		}
	}

	@Override
	public int getCurrentAgeByDate(int indexOfStudent) {
		int age = 0;
		if (indexOfStudent < 0) {
			throw new IllegalArgumentException();
		} else {
			for (int i = 0; i < students.length; i++) {
				if (students[i] != null && i == indexOfStudent) {
					Date now = new Date();
					long timeBetween = now.getTime() - students[i].getBirthDate().getTime();
					double yearsBetween = timeBetween / (3.156e+10);
					age = (int) Math.floor(yearsBetween);
				}
			}
		}
		return age;
	}

	@Override
	public Student[] getStudentsByAge(int age) {
		if (age <= 0) {
			throw new IllegalArgumentException();
		} else {
			int count = 0;
			for (int i = 0; i < students.length; i++) {
				if (students[i] != null && this.getCurrentAgeByDate(students[i].getId()) == age) {
					count++;
				}
			}

			if (count != 0) {
				int iter = 0;
				Student[] student = new Student[count];
				for (int i = 0; i < students.length; i++) {
					if (students[i] != null && this.getCurrentAgeByDate(students[i].getId()) == age) {
						student[iter++] = students[i];
					}
				}
				return student;
			} else {
				Student[] student = new Student[0];
				return student;
			}
		}
	}

	@Override
	public Student[] getStudentsWithMaxAvgMark() {
		int count = 0;
		double maxMark = students[0].getAvgMark();
		for (int i = 0; i < students.length; i++) {
			if (students[i] != null && students[i].getAvgMark() > maxMark) {
				maxMark = students[i].getAvgMark();
			}
		}
		for (int i = 0; i < students.length; i++) {
			if (students[i] != null && students[i].getAvgMark() == maxMark) {
				count++;
			}
		}
		Student[] student = new Student[count];
		int iter = 0;
		for (int i = 0; i < students.length; i++) {
			if (students[i] != null && students[i].getAvgMark() == maxMark) {
				student[iter++] = students[i];
			}
		}
		return student;
	}

	@Override
	public Student getNextStudent(Student student) {
		Student stud = null;
		if (student == null) {
			throw new IllegalArgumentException();
		} else {
			for (int i = 0; i < students.length; i++) {
				if (students[i] != null && students[i] == student) {
					if (i != students.length - 1) {
						stud = students[i + 1];
					} else {
						throw new IllegalArgumentException();
					}
				}
			}
		}
		return stud;
	}
}
