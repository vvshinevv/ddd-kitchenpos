package camp.nextstep.edu.racingcar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CarTest {

    @DisplayName("자동차 이름 5글자 미만 시 생성")
    @ParameterizedTest
    @ValueSource(strings = { "json", "kot", "mei" })
    void createCarWithUnder5LengthNameTest(String name) {
        Car car = new Car(name, 1);
        assertNotNull(car);
    }

    @DisplayName("자동차 이름 5글자 이상 시 생성")
    @ParameterizedTest
    @ValueSource(strings = { "jsonjson", "kotkot", "meimei" })
    void createCarWithOver5LengthNameTest(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Car(name, 1));
    }

    @DisplayName("자동차가 움직이는 조건을 충족하는 경우")
    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3 })
    void carCanMovableTest(int position) {
        final Car car = new Car("json", position);
        car.move(new TestMovingStrategy());
        assertThat(car.isInPosition(position + 1)).isTrue();
    }

    @DisplayName("자동차가 움직이는 조건을 충족하지 못하는 경우")
    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3 })
    void carCanNotMovableTest(int position) {
        final Car car = new Car("json", position);
        car.move(new TestNotMovingStrategy());
        assertThat(car.isInPosition(position)).isTrue();
    }

    class TestMovingStrategy implements MovingStrategy {
        @Override
        public boolean movable() {
            return true;
        }
    }

    class TestNotMovingStrategy implements MovingStrategy {
        @Override
        public boolean movable() {
            return false;
        }
    }
}