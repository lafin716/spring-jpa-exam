package com.lafin.springjpa01.repository;

import com.lafin.springjpa01.domain.Gender;
import com.lafin.springjpa01.domain.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;
import static org.springframework.data.domain.Sort.Order;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserHistoryRepository userHistoryRepository;

    @Test
    void crud() {
//        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
        List<User> users = userRepository.findAllById(Lists.newArrayList(1L, 2L, 3L));

        users.forEach(System.out::println);;
    }

    @Test
    void save() {
        User user1 = new User("jack", "jack@naver.com");
        User user2 = new User("admin", "admin@gabiacns.com");

        List<User> users = userRepository.saveAll(Lists.newArrayList(user1, user2));
        users.forEach(System.out::println);
    }

    @Test
    void findById() {
//        Optional<User> user = userRepository.findById(1L);
        // orElse로 널 처리를 하면 옵셔널을 사용할 필요가 없다
        User user = userRepository.findById(1L).orElse(null);

        System.out.println(user);
    }

    @Test
    void flush() {
        userRepository.save(new User("flusher", "flush@gmail.com"));
        userRepository.flush();
        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    void delete() {
        userRepository.delete(userRepository.findById(1L).orElseThrow(RuntimeException::new));
        userRepository.deleteById(2L);
        userRepository.deleteAll(userRepository.findAllById(Lists.newArrayList(1L, 3L)));
        userRepository.deleteAllInBatch(userRepository.findAllById(Lists.newArrayList(1L, 3L)));
    }

    @Test
    void paging() {
        Page<User> users = userRepository.findAll(PageRequest.of(0, 3));

        System.out.println("total rows : " + users.getTotalElements());
        System.out.println("total pages : " + users.getTotalPages());
        System.out.println("current rows : " + users.getNumberOfElements());
        System.out.println("size : " + users.getSize());
        System.out.println("sort : " + users.getSort());
        users.getContent().forEach(System.out::println);
    }

    @Test
    void queryMatcher() {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("name")
                .withMatcher("email", endsWith());

        Example<User> example = Example.of(new User("ma", "fastcampus.com"), matcher);

        userRepository.findAll(example).forEach(System.out::println);
    }

    @Test
    void queryMatcher2() {
        User user = User.builder().name("m").email("slow").build();

        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("email", contains());
        Example<User> example = Example.of(user, matcher);

        userRepository.findAll(example).forEach(System.out::println);
    }

    @Test
    void update() {
        userRepository.save(new User("david", "david@fastcampus.com"));

        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setEmail("martin-updated@fastcampus.com");

        userRepository.save(user);
    }

    @Test
    void userSelect() {
        System.out.println(userRepository.findByName("taemi"));

        System.out.println("findByEmail : " + userRepository.findByEmail("taemi@fastcampus.com"));
        System.out.println("getByEmail : " + userRepository.getByEmail("taemi@fastcampus.com"));
        System.out.println("readByEmail : " + userRepository.readByEmail("taemi@fastcampus.com"));
        System.out.println("queryByEmail : " + userRepository.queryByEmail("taemi@fastcampus.com"));
        System.out.println("searchByEmail : " + userRepository.searchByEmail("taemi@fastcampus.com"));
        System.out.println("streamByEmail : " + userRepository.streamByEmail("taemi@fastcampus.com"));
        System.out.println("findUserByEmail : " + userRepository.findUserByEmail("taemi@fastcampus.com"));
        System.out.println("findSomethingByEmail : " + userRepository.findSomethingByEmail("taemi@fastcampus.com"));

        System.out.println("findTop1ByName : " + userRepository.findTop1ByName("taemi"));
        System.out.println("findFirst1ByName : " + userRepository.findFirst1ByName("taemi"));

        System.out.println("findByEmailAndName : " + userRepository.findByEmailAndName("taemi@fastcampus.com", "taemi"));
        System.out.println("findByEmailOrName : " + userRepository.findByEmailOrName("taemi@fastcampus.com", "josh"));

        System.out.println("findByCreatedAtAfter : " + userRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByIdAfter : " + userRepository.findByIdAfter(4L));

        System.out.println("findByCreatedAtGreaterThan : " + userRepository.findByCreatedAtGreaterThan(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByCreatedAtGreaterThanEqual : " + userRepository.findByCreatedAtGreaterThanEqual(LocalDateTime.now().minusDays(1L)));

        // Between 에서는 <= , >= 형식이다.
        System.out.println("findByCreatedAtBetween : " + userRepository.findByCreatedAtBetween(LocalDateTime.now().minusDays(1L), LocalDateTime.now().plusDays(1L)));
        System.out.println("findByIdBetween : " + userRepository.findByIdBetween(1L, 3L));

        System.out.println("findByIdIsNotNull : " + userRepository.findByIdIsNotNull());
        // IsNotEmpty 는 컬렉션에 대응한다
//        System.out.println("findByAddressIsNotEmpty : " + userRepository.findByAddressIsNotEmpty());

        System.out.println("findByNameIn : " + userRepository.findByNameIn(Lists.newArrayList("margin", "taemi")));

        System.out.println("findByNameStartingWith : " + userRepository.findByNameStartingWith("t"));
        System.out.println("findByNameEndingWith : " + userRepository.findByNameEndingWith("h"));
        System.out.println("findByNameContains : " + userRepository.findByNameContains("tae"));
        System.out.println("findByNameLike : " + userRepository.findByNameLike("%aem%"));
    }

    @Test
    void pagingAndSorting() {
        System.out.println("findTop1ByName : " + userRepository.findTop1ByName("taemi"));
        System.out.println("findTopByNameOrderByIdDesc : " + userRepository.findTopByNameOrderByIdDesc("taemi"));
        System.out.println("findFirstByNameOrderByIdDescEmailAsc : " + userRepository.findFirstByNameOrderByIdDescEmailAsc("taemi"));
        System.out.println("findFirstByNameWithSort : " + userRepository.findFirstByName("taemi", getSort()));
        System.out.println("findFirstByNameWithSort : " + userRepository.findFirstByName("taemi", getSort()));

        System.out.println("findByNameWithPaging : " + userRepository.findByName("taemi", PageRequest.of(0, 1, Sort.by(Order.desc("id")))).getContent());
    }

    private Sort getSort() {
        return Sort.by(
                Order.desc("id"),
                Order.asc("email")
        );
    }

    @Test
    void insertAndUpdate() {
        User user = new User();
        user.setName("pjw");
        user.setEmail("pjw@gabiacns.com");

        userRepository.save(user);

        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user2.setName("pjjjjjw");

        userRepository.save(user2);
    }

    @Test
    void enumTest() {
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setGender(Gender.MALE);
        userRepository.save(user);

        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    void listenerTest() {
        User user = new User();
        user.setEmail("pjw@gabiacns.com");
        user.setName("pjw");

        userRepository.save(user);

        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        userRepository.save(user2);

        userRepository.deleteById(4L);
    }

    @Test
    void preUpdateTest() {
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);

        System.out.println("as-is : " + user);

        user.setName("margin22");
        userRepository.save(user);

        System.out.println("to-be : " + userRepository.findAll().get(0));
    }

    @Test
    void userHistoryTest() {
        User user = new User();
        user.setEmail("martin@fastcampus.com");
        user.setName("martin");

        userRepository.save(user);

        user.setName("martin-new");

        userRepository.save(user);

        userHistoryRepository.findAll().forEach(System.out::println);
    }
}