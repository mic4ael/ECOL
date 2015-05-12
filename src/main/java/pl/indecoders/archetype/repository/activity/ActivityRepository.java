package pl.indecoders.archetype.repository.activity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.indecoders.archetype.domain.activity.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {}
