# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure("2") do |config|
  config.vm.box = "savvydatainsights/ubuntu"

  config.vm.provider "virtualbox" do |v|
    v.linked_clone = true
  end

  config.vm.define "master" do |master|
    master.vm.hostname = "master"
    master.vm.network "private_network", ip: "192.168.33.10"
  end

  (1..2).each do |i|
    config.vm.define "slave#{i}" do |slave|
      slave.vm.hostname = "slave#{i}"
      slave.vm.network "private_network", ip: "192.168.33.#{i+1}0"

      if i == 2
        slave.vm.provision "ansible" do |ansible|
          ansible.limit = "all"
          ansible.playbook = "start-spark.yml"
          ansible.inventory_path = "inventory.yml"
        end
      end
    end
  end
end