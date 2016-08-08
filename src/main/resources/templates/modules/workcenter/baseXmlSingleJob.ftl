		<!-- ${tJob.functionName} -->
		<job>
			<name>${tJob.jobName}</name>
			<group>${tJob.jobGroup}</group>
			<description>${tJob.functionName}</description>
			<job-class>com.k2data.platform.modules.workcenter.quartz.job.${tJob.jobGroup?lower_case}.${tJob.jobName?cap_first}Job</job-class>
			<#if tJob.rootJob?? && tJob.rootJob != "1">
			<durability>true</durability>
			<recover>false</recover>
			</#if>
		</job>
		<#if tJob.rootJob?? && tJob.rootJob == "1">
		<trigger>
			<cron>
				<name>${tJob.jobName}_trigger</name>
				<group>${tJob.jobGroup}_trigger_group</group>
				<job-name>${tJob.jobName}</job-name>
				<job-group>${tJob.jobGroup}</job-group>
				<cron-expression>${tJob.cronExpression}</cron-expression>
			</cron>
		</trigger>
		</#if>